package MainSystem;

import java.util.Date;



//import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Cookies;

/**
 * A helper class that will try to persist data to local storage if supported, if not, then cookies are used.
 *
 * @author armang
 */
public class LocalStorage {

    public static  String readStoredItem(final String key) {
        if (Storage.isSupported()) {
            String item = Storage.getLocalStorageIfSupported().getItem(key);
            item = item == null ? "" : item;
//            Log.debug("Returning value:" + item + ", from local storage with key:" + key);
            return item;
        } else {
            // get from cookies
            String cookie = Cookies.getCookie(key);
//            Log.debug("Returning value:" + cookie + ", from cookie with key:" + key);
            return cookie;
        }
    }

    public static  void storeItem(final String key, final String value) {
        if (Storage.isSupported()) {
//            Log.debug("Storing value:" + value + ", with key:" + key + ", to local storage");
            Storage.getLocalStorageIfSupported().setItem(key, value);
        } else {
            // store to cookies
//            Log.debug("Storing value:" + value + ", with key:" + key + ", to cookie");
            Cookies.setCookie(key, value, new Date(Long.MAX_VALUE));
        }
    }

    public static  void removeItem(final String key) {
        if (Storage.isSupported()) {
//            Log.debug("Removing key:" + key + ", from local storage");
            Storage.getLocalStorageIfSupported().removeItem(key);
        } else {
            // store to cookies
//            Log.debug("Removing key:" + key + ", from cookies");
            Cookies.removeCookie(key);
        }
    }
}