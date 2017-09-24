<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
        version="1.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns="http://www.w3.org/1999/xhtml">

    <!--用于一个飞机的XSLT模板通过for-each元素可以重复使用，该元素用select特性来制定CML数据中的元素。
    select特性值是一个模式（pattern），该模式是制定某个元素的路径表达式，任何指定元素的子元素也包含在内-->
    <xsl:template match="planes">
        <h2>Airplane Description</h2>

        <xsl:for-each select="plane">
            <span style="font-size: italic;color:blue;">Year:</span>
            <xsl:value-of select="year"/>
            <br/>
            <span style="font-size: italic;color:blue;">Make:</span>
            <xsl:value-of select="make"/>
            <br/>
            <span style="font-size: italic;color:blue;">Model:</span>
            <xsl:value-of select="model"/>
            <br/>
            <span style="font-size: italic;color:blue;">Color:</span>
            <xsl:value-of select="color"/>
            <br/>
            <br/>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>