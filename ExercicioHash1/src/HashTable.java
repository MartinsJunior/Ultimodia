import java.util.LinkedList;

/**
 * Created by JjMacbook on 13/10/16.
 */

public class HashTable {
    private static double SCALE =0.4; //not applied ?
    private Element hTable[];
    private Element element;
    private int size=0;
    MeuHashEngine meuHashEngine;
    private int gambiarra[];
    private LinkedList<Object> objectLinkedList = new LinkedList();

    public HashTable(int tam,MeuHashEngine hashEngine){
        this.hTable = new Element[CalcTamVec(tam)];
        this.gambiarra = new int[CalcTamVec(tam)];
        this.meuHashEngine = hashEngine;
    }

    public HashTable(MeuHashEngine hashEngine) {
        this.hTable = new Element[CalcTamVec(1024)];
        this.meuHashEngine = hashEngine;
    }

    public long compressIndex(Object obj){
        long hashNumber = meuHashEngine.hashCode(obj);
        return (Math.abs(hashNumber) % this.hTable.length);
    }

    int CalcTamVec(int tamVec){
        return (int) (tamVec/SCALE);
    }

    public boolean setElement(Object obj, Object item){
        long hashNumber = compressIndex(obj);

        //Tratamento de colisão se a posição não estiver nula incrementa o contador HASH
        while(hTable[(int)hashNumber]!=null)hashNumber++;


        element = new Element(obj,item);
        if(!(hTable[(int)hashNumber]= element).equals(null)) {
            objectLinkedList.addLast(obj);
            size++;
            return true;
        }
        else return false;
    }

    public Object removeElement(Object obj){
        long hashNumber = compressIndex(obj);
        Object aux = hTable[(int)hashNumber];
        if(!aux.equals(null)){
            hTable[(int)hashNumber]=null;
            return obj;
        }
        return null;
    }

    public boolean existe(Object key){
        long hashNumber;
        for (int i = 0; i < size; i++) {
            hashNumber = compressIndex(objectLinkedList.get(i));
            if(hTable[(int)hashNumber].getKey().equals(key))return true;
        }
        return false;
    }


    public Object getElement(Object obj){
        long hashNumber = compressIndex(obj);
            if(existe(obj)){
            //se a posição não for NULL ela pode ter sofrido colisão
            //aqui se verifica se a posicão tem a mesma key que o objeto passado
            while(!hTable[(int)hashNumber].getKey().equals(obj))hashNumber++;
            return hTable[(int)hashNumber].getValue();
        }
        return null;
    }

    public LinkedList<Object> getObjectLinkedList() {
        return objectLinkedList;
    }
    public int getSize() {
        return size;
    }

}
