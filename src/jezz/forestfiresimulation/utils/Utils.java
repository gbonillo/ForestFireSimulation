package jezz.forestfiresimulation.utils;

/**
 *
 * @author jezz
 */
public class Utils {
    
    static public <T> boolean contains(T[] array, T toFind){
        if (array != null){
            for (int i=0; i<array.length; i++){
                if (toFind.equals(array[i])) return true;
            }
        }
        return false;
    }
    
}
