<?xml version="1.0" encoding="utf-8"?>
<!--一个XSLT样式表就是一个XML文档，其根元素是成为stylesheet的特殊用途的元素。stylesheet标签将名称空间定义为它的特性并封装了定义其转换的元素集合
该标签还将文档本身标识为一个XSLT文档。所有XSLT元素的名称空间通过一个W3C的URL指定。
如果此样式表包含XHTML元素，则stylesheet标签也需要制定XHTML的名称空间，它也是HTML的名称空间。
XSLT元素的前缀是xsl，而XHTML是默认名称空间-->
<xsl:stylesheet
        version="1.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns="http://www.w3.org/1999/xhtml"
>

    <!--XSLT样式表文档必须包含至少一个template元。模板的开始标签包含一个match特性来制定用于在XML文档中选择节点的类似于XPath的表达式。模板元素的内容指定了有哪些内容要放在输出文档中。-->
    <!--XSLT处理器会检查XML文档中的节点或者说元素，将它们与XSLT样式表中的模板进行比较
    如果有一个节点匹配某个模板，就将改模板添加到可应用模板的列表中。
    在简单的情况下，可能没有或者只有一个这样的模板，而在更一般的情况下，可能有一组复杂的规则确定了应用那个模板。-->

    <!--每个XSLT样式表都要为根节点包含一个模板。如果XSLT处理器的输出是HTML文档，则匹配根节点的模板被用来创建输出文档的HTML文档头部，模板元素的内容就是头部的代码-->
    <!--以斜杠开头的match属性的值是文档内的绝对地址，不以斜杠开头的是相对地址-->
    <xsl:template match="plane">
        <html>
            <head>
                <title>Style sheet for xslplane.xml</title>
            </head>
            <body>
                <h2>Airplane Description</h2>

                <!--Apply the matching templates to the elements in plane-->
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <!--The templates to be applied(by apply-templates) to the elements nested in the plane element-->
    <!--用于根节点的模板是隐式应用的，而XSLT文档的其他模板必须显式地应用于 XML文档-->
    <!--元素apply-templates用于将合适的模板应用于当前节点的后继节点上，该元素可以包含一个select属性来指定要把模板应用在哪些后继节点上-->
    <xsl:template match="year">
        <!--模板元素由两种不同的元素类型:一类包含实际内容;另一类则指定从相关联的XML文档复制的内容.
        用来表示HTML文档元素的XSLT元素常用于指定内容.它们与所关联的HTML文档元素的形式相同-->
        <!--XSLT处理器会将所有表示HTML元素的XSLT元素复制到产生的输出文档中-->
        <span style="font-size: italic;color: blue;">Year:</span>
        <!--value-of元素不能包含内容，select属性可以指定XML文档中的任何一个节点，特性值"."意味着选取当前元素下的所有元素，除非当前节点没有包含嵌套元素-->
        <xsl:value-of select="."/>
        <br/>
    </xsl:template>

    <xsl:template match="make">
        <span style="font-size: italic;color: blue;">Blue:</span>
        <xsl:value-of select="."/>
        <br/>
    </xsl:template>

    <xsl:template match="model">
        <span style="font-size: italic;color: blue;">Model:</span>
        <xsl:value-of select="."/>
        <br/>
    </xsl:template>
    <xsl:template match="color">
        <span style="font-size: italic;color: blue;">Color:</span>
        <xsl:value-of select="."/>
        <br/>
    </xsl:template>

</xsl:stylesheet>
