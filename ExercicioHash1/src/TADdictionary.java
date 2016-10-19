import java.util.LinkedList;

/**
 * Created by JjMacbook on 13/10/16.
 */
public interface TADdictionary {
    int tamanho();
    boolean vazio();
    LinkedList<Object> getkeys();
    LinkedList<Object> getItens();
    Object getElem(Object key);
    boolean insereElem(Object key, Object element);
    Object removeElem(Object key, int param4);
}
