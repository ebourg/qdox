package com.thoughtworks.qdox.parser.expression;

import java.util.List;


public class ElemValueListDef implements ElemValueDef{

	public List<ElemValueDef> valueList;
	
	public ElemValueListDef(List<ElemValueDef> annoValueList) {
		this.valueList = annoValueList;
	}

	public <U> U transform(AnnotationTransformer<U> transformer) {
		return transformer.transform(this);
	}
}
