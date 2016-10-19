/**
 * Created by JjMacbook on 13/10/16.
 */
public class Element {
    private Object key;
    private Object value;

    public Element() {

    }

    public Element(Object k, Object v){
        this.key = k;
        this.value = v;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}