# JasperReports® - Free Java Reporting Library

The JasperReports Library is the world's most popular open source reporting engine. 
It is entirely written in Java and it is able to use data coming from any kind of data source and 
produce pixel-perfect documents that can be viewed, printed or exported in a variety of document 
formats including HTML, PDF, Excel, OpenOffice and Word.


## iText 7 support version

Now *ONLY* SimplePdfTextRender.java is work

- Graphics2D is not supported
- TagHelper is not supported
- Bookmark is not supported
- Anchor is not supported


## You should apply the iText license key

Apply the license Key from https://pages.itextpdf.com/iText-7-Free-Trial-Landing-Page-1.html

And use the following codes in your main method.

import com.itextpdf.licensekey.LicenseKey;

LicenseKey.loadLicenseFile("c:\\itext-license.xml");