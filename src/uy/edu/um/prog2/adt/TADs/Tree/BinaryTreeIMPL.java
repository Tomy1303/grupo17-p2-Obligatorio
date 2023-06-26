package uy.edu.um.prog2.adt.TADs.Tree;

import uy.edu.um.prog2.adt.TADs.LinkedList.ListIMPL;

public class BinaryTreeIMPL<K extends Comparable<K>, T> implements MyBinaryTree<K, T> {

    private BinaryNodoTree<K,T> root;

    public BinaryTreeIMPL() {
        this.root = null;
    }

    @Override
    public T find(K key) throws EmptyTreeException {
        return find(key, this.root);//llama a una funcion recursiva
    }

    public T find(K key, BinaryNodoTree<K, T> nodo) throws EmptyTreeException {//funcion recursiva
        if(nodo == null){
            return null;                                            //si el nodo es nulo, no hay nada que buscar
        }else if(nodo.getKey().compareTo(key)==0){
            return nodo.getData();                                  //si el nodo es el que buscamos, devolvemos su data
        }else{
            if(find(key, nodo.getLeftChild()) != null) {            //si no es el nodo que buscamos, buscamos en sus hijos
                return find(key, nodo.getLeftChild());              //si el hijo izquierdo no es nulo, buscamos en el
            }else if(find(key, nodo.getRightChild()) != null){
                return find(key, nodo.getRightChild());             //si el hijo derecho no es nulo, buscamos en el
            }else{
                return null;                                        //si no es el nodo que buscamos y sus hijos son nulos, no hay nada que buscar
            }
        }
    }
    @Override
    public void insert(K key, T data) {
        insert(key, data, this.root);                                    //llamamos a la funcion recursiva
    }
    public void insert(K key,T data,BinaryNodoTree<K, T> nodo){         //funcion recursiva
        if (nodo != null) {//si el nodo no es nulo
            int compare = key.compareTo(nodo.getKey());                       //compara la key con la key del nodo
            if(compare < 0){
                if (nodo.getLeftChild() == null) {                      //si la key es menor a la del nodo, insertamos en el hijo izquierdo
                    nodo.setLeftChild(new BinaryNodoTree<>(key, data)); //si el hijo izquierdo es nulo, insertamos el nodo
                } else {
                    insert(key, data, nodo.getLeftChild());             //si el hijo izquierdo no es nulo, llamamos a la funcion recursiva con el hijo izquierdo
                }
            }else if(compare > 0){                 //si la key es mayor a la del nodo, insertamos en el hijo derecho
                if (nodo.getRightChild() == null) {                     //si el hijo derecho es nulo, insertamos el nodo
                    nodo.setRightChild(new BinaryNodoTree<>(key, data));//si el hijo derecho no es nulo, llamamos a la funcion recursiva con el hijo derecho
                } else {
                    insert(key, data, nodo.getRightChild());            //si el hijo derecho no es nulo, llamamos a la funcion recursiva con el hijo derecho
                }
            }else{
                nodo.setData(data);
            }                              //si la key es igual a la del nodo, cambiamos la data del nodo
        } else {
            this.root = new BinaryNodoTree<>(key, data);                //si el nodo es nulo, insertamos el nodo
        }
    }

    @Override
    public void delete(K key) throws EmptyTreeException {
        delete(key, this.root);                         //llama a una funcion recursiva
    }

    public void delete(K key, BinaryNodoTree<K, T> nodo) throws EmptyTreeException {//funcion recursiva
        if(nodo != null) {
            if(nodo.getKey().compareTo(key)==0){                                    //si el nodo es el que buscamos, lo eliminamos
                if(nodo.getLeftChild() != null) {                                   //si el hijo izquierdo no es nulo, cambiamos el nodo por el hijo izquierdo
                    nodo.setKey(nodo.getLeftChild().getKey());                      //cambiamos la key,Data del nodo izquierdo por el del nodo padre
                    nodo.setData(nodo.getLeftChild().getData());
                    if(nodo.getRightChild()!=null){                                 //si el hijo derecho del nodo izquierdo no es nulo, lo insertamos en el hijo derecho del nodo padr
                        BinaryNodoTree<K,T> toADD = nodo.getRightChild();
                        if(nodo.getLeftChild().getRightChild()!=null) {
                            nodo.setRightChild(nodo.getLeftChild().getRightChild());//si el hijo derecho del nodo izquierdo es nulo, cambiamos el hijo derecho del nodo padre por el del nodo izquierdo
                            BinaryNodoTree<K, T> aux = nodo.getRightChild();
                            while (aux.getRightChild() != null) {                       //buscamos el ultimo hijo derecho del ahora nodo padre para colocar el nodo derecho del anituguo nodo padre
                                aux = aux.getRightChild();
                            }
                            aux.setRightChild(toADD);                                   //colocamos el nodo derecho del antiguo nodo padre
                        }else{
                            nodo.setRightChild(toADD);
                        }
                    }else{
                        nodo.setRightChild(nodo.getLeftChild().getRightChild());    //si el hijo derecho del nodo izquierdo es nulo, cambiamos el hijo derecho del nodo padre por el del nodo izquierdo
                    }
                    nodo.setLeftChild(nodo.getLeftChild().getLeftChild());          //cambiamos el hijo izquierdo del nodo izquierdo por el del nodo padre
                }else {                                                              //si el hijo izquierdo es nulo, cambiamos el nodo por el hijo derecho
                    nodo.setKey(nodo.getRightChild().getKey());
                    nodo.setData(nodo.getRightChild().getData());
                    nodo.setLeftChild(nodo.getRightChild().getLeftChild());
                    nodo.setRightChild(nodo.getRightChild().getRightChild());
                }
            }else {                                                                 //si el nodo no es el que buscamos, buscamos en sus hijos
                if (find(key, nodo.getLeftChild()) != null) {                       //si el hijo izquierdo no es nulo, buscamos en el
                    delete(key, nodo.getLeftChild());
                } else if (find(key, nodo.getRightChild()) != null) {               //si el hijo derecho no es nulo, buscamos en el
                    delete(key, nodo.getRightChild());
                }
            }
        }
    }

    @Override
    public int size() {
        return inOrder().size();                                                     //retorna el tamaño del arbol
    }

    @Override
    public int countLeaf() {
        int count=0;
        return countLeaf(this.root, count);                                   //llama a una funcion recursiva
    }

    public int countLeaf(BinaryNodoTree<K, T> nodo, int count){               //funcion recursiva
        if(nodo == null){
            return count;                                                       //si el nodo es nulo, retornamos el contador
        }else if(nodo.getLeftChild() == null && nodo.getRightChild() == null){  //si el nodo es una hoja, aumentamos el contador
            count++;
            return count;
        }else{
            count = countLeaf(nodo.getLeftChild(), count);                      //si el nodo no es una hoja, buscamos en sus hijos
            count = countLeaf(nodo.getRightChild(), count);
            return count;
        }
    }

    @Override
    public int countCompleteElements() {
        int count=0;
        return countCompleteElements(this.root, count);                     //llama a una funcion recursiva
    }
    public int countCompleteElements(BinaryNodoTree<K, T> nodo, int count){ //funcion recursiva
        if(nodo == null){                                                     //si el nodo es nulo, retornamos el contador
            return count;
        }else if(nodo.getLeftChild() != null && nodo.getRightChild() != null){//si el nodo tiene dos hijos, aumentamos el contador
            count++;
            count = countCompleteElements(nodo.getLeftChild(), count);        //buscamos en sus hijos
            count = countCompleteElements(nodo.getRightChild(), count);
            return count;
        }else{
            count = countCompleteElements(nodo.getLeftChild(), count);       //si el nodo no tiene dos hijos, buscamos en sus hijos
            count = countCompleteElements(nodo.getRightChild(), count);
            return count;
        }
    }

    @Override
    public ListIMPL<K> inOrder() {
        return inOrder(this.root, new ListIMPL<>());
    }//llama a una funcion recursiva

    public ListIMPL<K> inOrder(BinaryNodoTree<K, T> nodo, ListIMPL<K> lista){    //funcion recursiva
        if (nodo != null) {                                                      //si el nodo no es nulo, buscamos en sus hijos
            inOrder(nodo.getLeftChild(), lista);                                 //buscamos en el hijo izquierdo
            lista.add(nodo.getKey());                                            //agregamos el nodo a la lista
            inOrder(nodo.getRightChild(), lista);                                //buscamos en el hijo derecho
        }
        return lista;
    }
    @Override
    public ListIMPL<K> preOrder() {//lo mismo que inOrder pero cambiamos el orden de las llamadas recursivas
        return preOrder(this.root, new ListIMPL<>());
    }
    public ListIMPL<K> preOrder(BinaryNodoTree<K, T> nodo, ListIMPL<K> lista){
        if (nodo != null) {
            lista.add(nodo.getKey());
            preOrder(nodo.getLeftChild(), lista);
            preOrder(nodo.getRightChild(), lista);
        }
        return lista;
    }

    @Override
    public ListIMPL<K> postOrder() {//lo mismo que inOrder pero cambiamos el orden de las llamadas recursivas
        return postOrder(this.root, new ListIMPL<>());
    }
    public ListIMPL<K> postOrder(BinaryNodoTree<K, T> nodo, ListIMPL<K> lista){
        if (nodo != null) {
            postOrder(nodo.getLeftChild(), lista);
            postOrder(nodo.getRightChild(), lista);
            lista.add(nodo.getKey());
        }
        return lista;
    }
}
