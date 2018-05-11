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

import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.property.BaseDirection;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.CanvasRenderer;
import com.itextpdf.layout.renderer.ParagraphRenderer;

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

		return pdfExporter.getPhrase(as, text, textElement);
	}

	
	@Override
	public void render()
	{
		
		float llx = x + leftPadding;
		// 座標位置，不可能於 pageHeight
		float lly = pdfExporter.getCurrentPageFormat().getPageHeight()
				- y
				- topPadding
				- verticalAlignOffset;
//		log.debug("pdfExporter.getCurrentPageFormat().getPageHeight: " + pdfExporter.getCurrentPageFormat().getPageHeight()
//				+ ", topPadding: " + topPadding
//				+ ", verticalAlignOffset: " + verticalAlignOffset
//				+ ", text.getTextHeight: " + text.getTextHeight()
//				);
		float rectWidth = x + width - rightPadding - (x + leftPadding);
		float rectHeight = -text.getHeight() + text.getTextHeight() + verticalAlignOffset;
//		log.debug("y: " + y
//				+ ", height: " + height
//				+ ", bottomPadding: " + bottomPadding
//				+ ", pdfExporter.getCurrentPageFormat().getPageHeight(): " + pdfExporter.getCurrentPageFormat().getPageHeight()
//				+ ", y: " + y
//				+ ", topPadding: " + topPadding
//				+ ", verticalAlignOffset: " + verticalAlignOffset
//				+ ", text.getLeadingOffset(): " + text.getLeadingOffset());
		log.debug("llx = "+ llx);
		log.debug("lly = "+ lly);
		log.debug("width = "+rectWidth);
		log.debug("height = " + rectHeight);
		
		
		Rectangle rectangle = new Rectangle( llx, lly, rectWidth, rectHeight );
		Paragraph paragraph = getPhrase(styledText, text);
		
//		log.debug("text= "+ styledText.getText());
//		log.debug("text.getHeight: " + text.getHeight()
//				+ ", text.getTextHeight: " + text.getTextHeight()
//				+ ", text.getWidth: " + text.getWidth());
		paragraph
			.setTextAlignment( (horizontalAlignment == TextAlignment.JUSTIFIED_ALL)? TextAlignment.JUSTIFIED : horizontalAlignment )
			.setFixedLeading(text.getLineSpacingFactor())
			.setBaseDirection((text.getRunDirectionValue() == RunDirectionEnum.LTR) ? BaseDirection.LEFT_TO_RIGHT : BaseDirection.RIGHT_TO_LEFT);
		
		
		paragraph.setNextRenderer(new ParagraphRenderer(paragraph) {
	        @Override
	        public List<Rectangle> initElementAreas(LayoutArea area) {
	            List<Rectangle> list = new ArrayList<Rectangle>();
	            list.add(rectangle);
	            return list;
	        }
	    });
		
		Canvas canvas = new Canvas(pdfCanvas, pdfDocument, rectangle);
		canvas.add(paragraph);
		canvas.close();
	}


	@Override
	public void draw()
	{
		//nothing to do
	}
}
