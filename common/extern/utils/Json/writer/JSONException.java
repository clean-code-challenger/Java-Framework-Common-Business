package common.extern.utils.Json.writer;

public class JSONException extends Exception
{

    /****/
	private static final long serialVersionUID = 1L;

	public JSONException(String message)
    {
        super(message);
    }

    public JSONException(Throwable cause)
    {
        super(cause);
    }

    public JSONException(String message, Throwable cause)
    {
        super(message, cause);
    }
}