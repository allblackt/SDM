<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
	<xsl:template match="root">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait" page-height="297mm" page-width="210mm" margin-top="1cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
					<fo:region-body margin="1cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">
				<fo:block border-width="1mm" border-style="solid">
				  This block of output will have a one millimeter border around it.
				</fo:block>
				<fo:block>
					<fo:inline>Any text, intermingled with:
					   <fo:block>Any text, intermingled with:
					      <fo:block>Any text, intermingled with:
					         <fo:block>Any text, intermingled with:...
					         </fo:block>
					      </fo:block>
					   </fo:block>
					</fo:inline>
				</fo:block>
				
				<fo:block text-align-last="justify">
				  LEFT TEXT
				  <fo:leader leader-pattern="space" />
				  RIGHT TEXT
				</fo:block>

				
				<fo:block>
			     <fo:inline border="1px">aaa</fo:inline>
			     <fo:inline width="100mm" border-width="1mm" border-style="solid" margin-left="100"> bbb</fo:inline>
			  </fo:block>

				<fo:block>
						Hello,
						<xsl:value-of select="name" />
						!
					</fo:block>
					<fo:block>
						<fo:table>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell border="solid 1px black"
										text-align="center" font-weight="bold">
										<fo:block>
											No.
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black"
										text-align="center" font-weight="bold">
										<fo:block>
											Name
										</fo:block>
									</fo:table-cell>
									<fo:table-cell border="solid 1px black"
										text-align="center" font-weight="bold">
										<fo:block>
											Phone Number
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<xsl:for-each select="./friend">
									<fo:table-row>
										<fo:table-cell border="solid 1px black"
											text-align="center">
											<fo:block>
												<xsl:value-of select="position()" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 1px black"
											text-align="center">
											<fo:block>
												<xsl:value-of select="name" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell border="solid 1px black"
											text-align="center">
											<fo:block>
												<xsl:value-of select="phNo" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>