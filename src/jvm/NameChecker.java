package jvm;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;
import java.util.EnumSet;

/**
 * 程序名称规范的编译器插件
 * 如果程序命名不合规范，就会输出一个编译器的warning信息
 */
public class NameChecker {
    private final Messager messager;

    NameCheckScanner nameCheckScanner=new NameCheckScanner();

    NameChecker(ProcessingEnvironment processingEnvironment){
        this.messager=processingEnvironment.getMessager();
    }

    /**
     * 对Java程序命名进行检查，根据《Java语言规范》第三版第6.8节的要求，Java程序命名应当符合下列格式：
     *
     * <ul>
     * <li>类或接口：符合驼式命名法，首字母大写。
     * <li>方法：符合驼式命名法，首字母小写。
     * <li>字段：
     * <ul>
     * <li>类、实例变量: 符合驼式命名法，首字母小写。
     * <li>常量: 要求全部大写。
     * </ul>
     * </ul>
     */
    public void checkNames(Element element){

    }

    /**
     * 名称检查器实现类，继承了JDK 1.6中新提供的ElementScanner6<br>
     * 将会以Visitor模式访问抽象语法树中的元素
     */
    private class NameCheckScanner extends ElementScanner6<Void,Void>{
        /**
         * 此方法用于检查java类
         * @param e
         * @param p
         * @return
         */
        @Override
        public Void visitType(TypeElement e,Void p){
            scan(e.getTypeParameters(),p);
            checkCamelCase(e,true);
            super.visitType(e,p);
            return null;
        }

        /**
         * 检查方法命名是否合法
         * @param e
         * @param p
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e,Void p){
            if(e.getKind()==ElementKind.METHOD){
                Name name=e.getSimpleName();
                if(name.contentEquals(e.getEnclosingElement().getSimpleName())){
                    messager.printMessage(Diagnostic.Kind.WARNING,"一个普通方法"+name+"不该与类名重复，避免与构造函数产生混淆",e);
                    checkCamelCase(e,false);
                }
            }
            super.visitExecutable(e,p);
            return null;
        }

        /**
         * 检查变量命名是否合法
         * @param e
         * @param p
         * @return
         */
        @Override
        public Void visitVariable(VariableElement e, Void p) {
            //如果这个变量是枚举或常量，则按大写命名检查，否则按照驼式命名法规则检查
            if(e.getKind()==ElementKind.ENUM_CONSTANT||e.getConstantValue()!=null||heuristicallyConstant(e)){
                checkAllCaps(e);
            }else{
                checkCamelCase(e,false);
            }
            return null;
        }

        /**
         * 判断一个变量是否是常量
         * @param e
         * @return
         */
        private boolean heuristicallyConstant(VariableElement e){
            if(e.getEnclosingElement().getKind()==ElementKind.INTERFACE){
                return true;
            }else if(e.getKind()==ElementKind.FIELD&&e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC,Modifier.STATIC,Modifier.FINAL))){
                return true;
            }else{
                return false;
            }
        }

        /**
         * 检查传入的Element是否符合驼峰命名法，如果不符合则输出警告信息
         * @param e
         * @param initialCaps 首字母是否需要大写
         */
        private void checkCamelCase(Element e,boolean initialCaps){
            String name=e.getSimpleName().toString();
            boolean previousUpper=false;
            boolean conventional=true;
            int firstCodePoint=name.codePointAt(0);
            if(Character.isUpperCase(firstCodePoint)){
                previousUpper=true;
                if(!initialCaps){
                    messager.printMessage(Diagnostic.Kind.WARNING,"名称"+name+"应当以小写开头",e);
                }
                return;
            }else if(Character.isLowerCase(firstCodePoint)){
                if(initialCaps){
                    messager.printMessage(Diagnostic.Kind.WARNING,"名称"+name+"应当以大写写开头",e);
                }
                return;
            }else{
                conventional=false;//=true?
            }
            //todo 没看懂
            if(conventional){
                int cp=firstCodePoint;
                for(int i=Character.charCount(cp);i<name.length();i+=Character.charCount(cp)){
                    cp=name.codePointAt(i);
                    if(Character.isUpperCase(cp)){
                        if(previousUpper){
                            conventional=false;
                            break;
                        }
                        previousUpper=true;
                    }else{
                        previousUpper=false;
                    }
                }
            }
            if(!conventional){
                messager.printMessage(Diagnostic.Kind.WARNING,"名称"+name+"应当符合驼峰命名法",e);
            }

        }

        /**
         * 大写命名检查，要求第一个字母必须是大写的英文字母，其余部分可以使下划线或大写字母，数字
         * @param e
         */
        private void checkAllCaps(Element e){
            String name=e.getSimpleName().toString();
            boolean conventional=true;
            int firstCodePoint=name.codePointAt(0);
            //是否以大写字母开头
            if(!Character.isUpperCase(firstCodePoint)){
                conventional=false;
            }else{
                boolean previousUnderScore=false;
                int cp=firstCodePoint;
                for(int i=Character.charCount(cp);i<name.length();i+=Character.charCount(cp)){
                    cp=name.codePointAt(i);
                    //不能有两个连续的下划线
                    if(cp==(int)'_'){
                        if(previousUnderScore){
                            conventional=false;
                            break;
                        }
                        previousUnderScore=true;
                    }else{
                        previousUnderScore=false;
                        //必须是大写字母或者数字
                        if(!Character.isUpperCase(cp)&&!Character.isDigit(cp)){
                            conventional=false;
                            break;
                        }
                    }
                }
            }
            if(!conventional){
                messager.printMessage(Diagnostic.Kind.WARNING,"常量"+name+"应当全部以大写字母或下划线命名，并且以字母开头",e);
            }

        }
    }

}
