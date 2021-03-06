/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2016 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.export;

import java.awt.Color;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.BaseDirection;
import com.itextpdf.layout.property.TextAlignment;

//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Phrase;
//import com.lowagie.text.pdf.ColumnText;
//import com.lowagie.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.type.RunDirectionEnum;
import net.sf.jasperreports.engine.util.JRStyledText;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class SimplePdfTextRenderer extends AbstractPdfTextRenderer
{
	
	private static final Log log = LogFactory.getLog(SimplePdfTextRenderer.class);
	/**
	 * 
	 */
	public SimplePdfTextRenderer(JasperReportsContext jasperReportsContext, boolean ignoreMissingFont)
	{
		super(jasperReportsContext, ignoreMissingFont);
	}
	
	
	/**
	 *
	 */
	protected Paragraph getPhrase(JRStyledText styledText, JRPrintText textElement)
	{
		String text = styledText.getText();

		AttributedString as = styledText.getAttributedString();

		AttributedCharacterIterator asIterator = as.getIterator();
		
		log.debug("AttributedCharacterIterator -- ");
		log.debug(asIterator.getAttributes());
		
		
			
		
		return pdfExporter.getPhrase(as, text, textElement);
	}

	
	@Override
	public void render()
	{
		/*
		float llx = x + leftPadding;
		// y 軸座標位置，不可能大於 pageHeight
		float lly = pdfExporter.getCurrentPageFormat().getPageHeight()
				- y
<<<<<<< HEAD
				//- topPadding
				 - verticalAlignOffset
				// - text.getLeadingOffset()
				+ text.getHeight();
		float rectWidth = x + width - rightPadding - (x + leftPadding);
		float rectHeight = pdfExporter.getCurrentPageFormat().getPageHeight()
				- y
				- height
				+ bottomPadding - (pdfExporter.getCurrentPageFormat().getPageHeight()
				- y
				//- topPadding
				// - verticalAlignOffset
				 - text.getLeadingOffset()
				//+ text.getHeight()
				);
		
		*/
		
		float llx = x + leftPadding;
		float lly = pdfExporter.getCurrentPageFormat().getPageHeight() - y - verticalAlignOffset - text.getLeadingOffset() - height; //text.getHeight();
		float rectWidth  = width;
		float rectHeight = height;
		

		log.debug("llx = " + llx);
		log.debug("lly = " + lly);
		log.debug("width = " + rectWidth);
		log.debug("height = " + rectHeight);
		
		Rectangle rectangle = new Rectangle( llx, lly, rectWidth, rectHeight );
		
		
//		pdfCanvas.rectangle(rectangle);
//		pdfCanvas.stroke();
		
		Canvas canvas = new Canvas(pdfCanvas, pdfDocument, rectangle);
		
		
		Paragraph paragraph = getPhrase(styledText, text);
		
		log.debug("getLeadingOffset = "+text.getLeadingOffset());
		log.debug("getLineSpacingFactor = "+text.getLineSpacingFactor());
		
		paragraph
			.setTextAlignment( (horizontalAlignment == TextAlignment.JUSTIFIED_ALL)? TextAlignment.JUSTIFIED : horizontalAlignment )
			.setFixedLeading(0)
			// TODO background 如果沒填滿 canvas 會有問題
//			.setBackgroundColor(new DeviceRgb(text.getBackcolor()))
			.setMultipliedLeading(text.getLineSpacingFactor())
			.setBaseDirection((text.getRunDirectionValue() == RunDirectionEnum.LTR) ? BaseDirection.LEFT_TO_RIGHT : BaseDirection.RIGHT_TO_LEFT);
		
//		paragraph.setNextRenderer(new ParagraphRenderer(paragraph) {
//	        @Override
//	        public List<Rectangle> initElementAreas(LayoutArea area) {
//	            List<Rectangle> list = new ArrayList<Rectangle>();
//	            list.add(rectangle);
//	            return list;
//	        }
//	    });
		canvas.add(paragraph);
		
		canvas.close();
	}


	@Override
	public void draw()
	{
		//nothing to do
	}
}
