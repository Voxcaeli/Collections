package collections;

import java.util.NoSuchElementException;

public class IntStack extends IntArray {
    // FIELDS - поля класса
    /**
     * Индекс последнего элемента стека
     * */
    protected int lastIndex;


    // CONSTRUCTORS - конструкторы класса
    /**
     * Конструктор стека по умолчанию
     * */
    public IntStack() {
        super();
        lastIndex = -1;
    }
    /**
     * Конструктор стека с указанием его вместимости
     * @param capacity Вместимость будущего стека
     * */
    public IntStack(int capacity) {
        super(capacity);
        lastIndex = -1;
    }
    /**
     * Конструктор стека с указанием списка добавляемых значений
     * @param values Список добавляемых значений
     * */
    public IntStack(int... values) {
        super(values);
        lastIndex = size - 1;
    }
    /**
     * Конструктор стека с указанием его вместимости и списком добавляемых значений
     * @param capacity Вместимость будущего стека
     * @param values Список добавляемых значений
     * */
    public IntStack(int capacity, int... values) {
        super(capacity, values);
        lastIndex = size - 1;
    }


    // GETTERS - получение данных
    /**
     * Получение элемента верхушки стека
     * @return Элемент верхушки стека
     * @exception NoSuchElementException Ошибка получения элемента из пустого стека
     * **/
    public int getLast() {
        if (size == 0) {
            throw new NoSuchElementException("В стеке отсутствуют элементы");
        }
        return collection[lastIndex];
    }

    /**
     * Получение элемента верхушки стека
     * @return Элемент верхушки стека типа {@link Integer}
     * **/
    public Integer peekLast() {
        if (size == 0) {
            return null;
        }
        return collection[lastIndex];
    }


    // ADD - добавление данных
    /**
     * Добавление элемента в стек. Копирует метод {@link IntStack#addLast(int)}
     * @param value Значение добавляемого элемента
     * @exception ArrayStoreException Ошибка переполнения стека
     * */
    @Override
    public void add(int value) {
        addLast(value);
    }

    /**
     * Добавление элемента в стек
     * @param value Значение добавляемого элемента
     * @exception ArrayStoreException Ошибка переполнения стека
     * */
    public void addLast(int value) {
        if (size == capacity) {
            throw new ArrayStoreException("Стек заполнен. Добавление нового элемента невозможно");
        }
        collection[size] = value;
        size++;
        lastIndex++;
    }

    /**
     * Добавление элемента в стек. Копирует метод {@link IntStack#offerLast(int)}
     * @param value Значение добавляемого элемента
     * @return true - если новый элемент добавлен в массив, иначе - false
     * */
    @Override
    public boolean offer(int value) {
        return offerLast(value);
    }

    /**
     * Добавление элемента в стек
     * @param value Значение добавляемого элемента
     * @return true - если новый элемент добавлен в массив, иначе - false
     * */
    public boolean offerLast(int value) {
        if (size == capacity) {
            return false;
        }
        collection[size] = value;
        size++;
        lastIndex++;
        return true;
    }


    // SETTERS - изменение данных
    /**
     * Установка значения элементу верхушки стека
     * @param value Новое значение элемента верхушки стека
     * @exception NoSuchElementException Ошибка изменение элемента пустого стека
     **/
    public void setLast(int value) {
        if (size == 0) {
            throw new NoSuchElementException("В стеке отсутствуют элементы");
        }
        collection[lastIndex] = value;
    }


    // REMOVE - удаление данных
    /**
     * Удаление элемента верхушки стека с возвращением удалённого элемента
     * @return Удалённый элемент верхушки стека
     * @exception NoSuchElementException Ошибка удаления элемента в пустом стеке
     **/
    public int removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("В стеке отсутствуют элементы");
        }
        int value = collection[lastIndex];
        collection[lastIndex] = 0;
        lastIndex--;
        size--;
        return value;
    }

    /**
     * Удаление элемента верхушки стека с возвращением удалённого элемента
     * @return Удалённый элемент верхушки стека типа {@link Integer}
     **/
    public Integer pollLast() {
        if (size == 0) {
            return null;
        }
        int value = collection[lastIndex];
        collection[lastIndex] = 0;
        lastIndex--;
        size--;
        return value;
    }


    // TO_STRING - строковое преобразование данных
    /**
     * Вывод содержимого элементов стека, вместе с их индексами
     * @return Строка, состоящая из элементов стека, включая их индексы
     * */
    public String deepToString() {
        if (size == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        result.append("[0]=").append(collection[0]);

        for (int i = 1; i < size; i++) {
            result.append("; [").append(i).append("]=").append(collection[i]);
        }
        return result.toString();
    }
}
