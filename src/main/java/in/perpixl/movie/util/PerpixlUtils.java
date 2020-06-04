package in.perpixl.movie.util;

import java.util.Collections;
import java.util.List;

public class PerpixlUtils {
	public static <T> List<T> safe( List<T> other ) {
	    return other == null ? Collections.EMPTY_LIST : other;
	}
}
