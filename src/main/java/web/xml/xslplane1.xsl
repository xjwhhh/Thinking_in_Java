<?xml version="1.0" encoding="utf-8"?>
<!--没必要为plane的所有子节点都包含模板，因为value-of元素的select子句可以找到这些子节点-->
<xsl:stylesheet
        version="1.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns="http://www.w3.org/1999/xhtml"
>
    <xsl:template match="plane">
        <html>
            <head>
                <title>Style sheet for xslplane.xml</title>
            </head>
            <body>
                <h2>Airplane Description</h2>
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
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>