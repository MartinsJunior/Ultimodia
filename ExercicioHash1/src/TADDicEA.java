import java.util.LinkedList;

/**
 * Created by JjMacbook on 14/10/16.
 */
public class TADDicEA implements TADdictionary {
    private HashTable hTable;

    public TADDicEA(int tam, MeuHashEngine meuhashEngine){
        this.hTable = new HashTable(tam,meuhashEngine);
    }

    public TADDicEA(MeuHashEngine meuhashEngine){
        this.hTable = new HashTable(meuhashEngine);
    }
    @Override
    public int tamanho() {//OK
        return hTable.getSize();
    }

    @Override
    public boolean vazio() {//OK
        if(hTable.getSize()>0)return false;
        else{
            return true;
        }
    }

    @Override
    public LinkedList<Object> getkeys() {//OK
        return hTable.getObjectLinkedList();
    }

    @Override //OK
    public LinkedList<Object> getItens() {
        LinkedList<Object> itens = new LinkedList<>();
        for (int i = 0; i < hTable.getObjectLinkedList().size(); i++) {
            itens.addLast(getElem(hTable.getObjectLinkedList().get(i).toString()));
        }
        return itens;
    }


    public boolean existe(Object obj){
        return hTable.existe(obj);
    }
    @Override
    public Object getElem(Object key) {//OK
        return hTable.getElement(key);

    }

    @Override
    public boolean insereElem(Object key, Object element) {//OK
        if(hTable.setElement(key,element))return true;
        else return false;
    }

    @Override
    public Object removeElem(Object key,int param4) {
        return hTable.removeElement(key);
    }
}
