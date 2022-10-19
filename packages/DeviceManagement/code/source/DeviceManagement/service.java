package DeviceManagement;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
// --- <<IS-END-IMPORTS>> ---

public final class service

{
	// ---( internal utility methods )---

	final static service _instance = new service();

	static service _newInstance() { return new service(); }

	static service _cast(Object o) { return (service)o; }

	// ---( server methods )---




	public static final void GetDateTimeWithOffset (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(GetDateTimeWithOffset)>> ---
		// @sigtype java 3.5
		// [i] field:0:required dateTimeIn
		// [i] field:0:required offset
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	dateTimeIn = IDataUtil.getString( pipelineCursor, "dateTimeIn" );
			String	offset = IDataUtil.getString( pipelineCursor, "offset" );
		pipelineCursor.destroy();
		
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss z");
		ZonedDateTime zdt  = ZonedDateTime.parse(dateTimeIn + " UTC", dtf);        
		Long epoch = zdt.toInstant().toEpochMilli();
		
		epoch = epoch + Long.parseLong(offset) * 1000;
		
		SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String dateTimeOut = tdf.format(epoch);
		
		//dtf.format(Instant.ofEpochMilli(epoch));
		
		IDataUtil.put(pipelineCursor, "dateTimeOut", dateTimeOut);
		
		// pipeline
		
			
		// --- <<IS-END>> ---

                
	}
}

