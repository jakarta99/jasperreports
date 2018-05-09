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
package net.sf.jasperreports.export.type;

import com.itextpdf.kernel.pdf.EncryptionConstants;

import net.sf.jasperreports.engine.type.EnumUtil;
import net.sf.jasperreports.engine.type.NamedEnum;


/**
 * @author sanda zaharia (shertage@users.sourceforge.net)
 */
public enum PdfPermissionsEnum implements NamedEnum
{
	/**
	 * All user permissions
	 */
	ALL(EncryptionConstants.ALLOW_PRINTING 
			| EncryptionConstants.ALLOW_MODIFY_CONTENTS 
			| EncryptionConstants.ALLOW_COPY 
			| EncryptionConstants.ALLOW_MODIFY_ANNOTATIONS 
			| EncryptionConstants.ALLOW_FILL_IN 
			| EncryptionConstants.ALLOW_SCREENREADERS 
			| EncryptionConstants.ALLOW_ASSEMBLY 
			|EncryptionConstants.ALLOW_DEGRADED_PRINTING, "ALL"),
	/**
	 * Assembly permission
	 */
	ASSEMBLY(EncryptionConstants.ALLOW_ASSEMBLY, "ASSEMBLY"),
	
	/**
	 * Copy permission
	 */
	COPY(EncryptionConstants.ALLOW_COPY, "COPY"),
	
	/**
	 * Degraded printing permission
	 */
	DEGRADED_PRINTING(EncryptionConstants.ALLOW_DEGRADED_PRINTING, "DEGRADED_PRINTING"),
	
	/**
	 * Fill in forms permission
	 */
	FILL_IN(EncryptionConstants.ALLOW_FILL_IN, "FILL_IN"),
	
	/**
	 * Modify annotations permission
	 */
	MODIFY_ANNOTATIONS(EncryptionConstants.ALLOW_MODIFY_ANNOTATIONS, "MODIFY_ANNOTATIONS"),
	
	/**
	 * Modify contents permission
	 */
	MODIFY_CONTENTS(EncryptionConstants.ALLOW_MODIFY_CONTENTS, "MODIFY_CONTENTS"),
	
	/**
	 * Print permission
	 */
	PRINTING(EncryptionConstants.ALLOW_PRINTING, "PRINTING"),
	
	/**
	 * Screen readers permission
	 */
	SCREENREADERS(EncryptionConstants.ALLOW_SCREENREADERS, "SCREENREADERS");
	
	
	/**
	 *
	 */
	private final transient int value;
	private final transient String name;

	private PdfPermissionsEnum(int value, String name) 
	{
		this.value = value;
		this.name = name!= null ? name.toUpperCase() : null;
	}

	/**
	 *
	 */
	public Integer getPdfPermission() 
	{
		return value;
	}
	
	@Override
	public String getName() 
	{
		return name;
	}
	
	/**
	 *
	 */
	public static PdfPermissionsEnum getByName(String name) 
	{
		return name == null ? null : EnumUtil.getEnumByName(values(), name.toUpperCase());
	}
}
