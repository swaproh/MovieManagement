package in.perpixl.movie.util;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PerpixlUtils {
	public static <T> List<T> safe( List<T> other ) {
	    return other == null ? Collections.EMPTY_LIST : other;
	}
	
	public static <T> Set<T> safe( Set<T> other ) {
	    return other == null ? Collections.EMPTY_SET : other;
	}
	
	public static <T> boolean isNullObject(T object)
	{
		return object == null;
	}
	
	public static <T> boolean isNotNullObject(T object)
	{
		return !isNullObject(object);
	}
}
