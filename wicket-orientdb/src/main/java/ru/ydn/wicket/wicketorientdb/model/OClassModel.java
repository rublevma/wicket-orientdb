package ru.ydn.wicket.wicketorientdb.model;

import org.apache.wicket.model.LoadableDetachableModel;

import ru.ydn.wicket.wicketorientdb.OrientDbWebSession;

import com.orientechnologies.orient.core.db.record.ODatabaseRecord;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class OClassModel extends LoadableDetachableModel<OClass> {

	private String className;
	
	public OClassModel(OClass oClass) {
		super(oClass);
		className=oClass.getName();
	}

	public OClassModel(String className) {
		this.className=className;
	}

	
	
	

	@Override
	protected OClass load() {
		return className!=null?getSchema().getClass(className):null;
	}
	
	@Override
    public void detach()
    {
		OClass oClass = getObject();
		if(oClass!=null)
		{
	        this.className = oClass.getName();
	        super.detach();
		}
    }
	
	public OSchema getSchema()
	{
		return getDatabase().getMetadata().getSchema();
	}
	
	public ODatabaseRecord getDatabase()
	{
		return OrientDbWebSession.get().getDatabase();
	}

}