var name="1"
var object= {
    name: "2",
    getName: function () {
        alert(this.name);
        return function () {
            alert(this.name);
            return this.name;
        };
    }
};
object2 = {
    name:"we",
    getName:function(){
        alert(object.getName()())
    }
};
object2.getName();
