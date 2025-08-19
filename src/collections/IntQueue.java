package collections;

import com.sun.tools.jconsole.JConsoleContext;

import java.util.NoSuchElementException;

public class IntQueue extends IntArray {
    // FIELDS - поля класса
    /**
     * Индекс первого элемента очереди
     * */
    protected int firstIndex;
    /**
     * Индекс последнего элемента очереди
     * */
    protected int lastIndex;


    // CONSTRUCTORS - конструкторы класса
    /**
     * Конструктор очереди по умолчанию
     * */
    public IntQueue() {
        super();
        firstIndex = -1;
        lastIndex = -1;
    }

    /**
     * Конструктор очереди с указанием её вместимости
     * @param capacity Вместимость будущей очереди
     * @exception NegativeArraySizeException Ошибка указания отрицательного значения вместимости очереди
     * */
    public IntQueue(int capacity) {
        super(capacity);
        firstIndex = -1;
        lastIndex = -1;
    }

    /**
     * Конструктор очереди с указанием списка добавляемых значений
     * @param values Список добавляемых значений
     * */
    public IntQueue(int... values) {
        super(values);
        firstIndex = 0;
        lastIndex = size - 1;
    }
    /**
     * Конструктор очереди с указанием её вместимости и списком добавляемых значений
     * @param capacity Вместимость будущей очереди
     * @param values Список добавляемых значений
     * @exception NegativeArraySizeException Ошибка указания отрицательного значения вместимости очереди
     * */
    public IntQueue(int capacity, int... values) {
        super(capacity, values);
        firstIndex = 0;
        lastIndex = size - 1;
    }

    // SERVICES - служебные методы
    /**
     * Проверка допустимости индекса
     * @param index Проверяемый индекс
     * @exception ArrayIndexOutOfBoundsException Ошибка указания индекса за пределами вместимости очереди
     * @exception ArrayIndexOutOfBoundsException Ошибка отсутствия элемента по указанному индексу
     * */
    @Override
    protected void checkIndex(int index) {
        if (index == capacity || Math.abs(index) > capacity) {
            throw new ArrayIndexOutOfBoundsException("Индекс " + index + " выходит за пределы вместимости очереди");
        }

        if (firstIndex > lastIndex) {
            if (index < firstIndex && index > lastIndex) {
                throw new ArrayIndexOutOfBoundsException("Элемент по индексу" + index + " отсутствует");
            }
        } else {
            if (index < firstIndex || index > lastIndex) {
                throw new ArrayIndexOutOfBoundsException("Элемент по индексу" + index + " отсутствует");
            }
        }
    }


    // GETTERS - получение данных
    /**
     * Получение элемента из начала очереди
     * @return Элемент начала очереди
     * @exception NoSuchElementException Ошибка получения элемента из пустой очереди
     * **/
    public int getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("В очереди отсутствуют элементы");
        }
        return collection[firstIndex];
    }

    /**
     * Получение элемента из начала очереди
     * @return Элемент начала очереди типа {@link Integer}
     * **/
    public Integer peekFirst() {
        if (size == 0) {
            return null;
        }
        return collection[firstIndex];
    }

    /**
     * Получение минимального значения элементов очереди
     * @return Минимальное значение элементов очереди типа {@link Integer}
     * */
    @Override
    public Integer getMin() {
        if (size == 0) {
            return null;
        }
        int min = Integer.MAX_VALUE;

        if (firstIndex <= lastIndex) {
            for (int i = 0; i < size; i++) {
                if (collection[i] < min) {
                    min = collection[i];
                }
            }
        } else {
            for (int i = firstIndex; i < capacity; i++) {
                if (collection[i] < min) {
                    min = collection[i];
                }
            }

            for (int i = 0; i <= lastIndex; i++) {
                if (collection[i] < min) {
                    min = collection[i];
                }
            }
        }
        return min;
    }

    /**
     * Получение максимального значения элементов очереди
     * @return Максимальное значение элементов очереди типа {@link Integer}
     * */
    @Override
    public Integer getMax() {
        if (size == 0) {
            return null;
        }
        int max = Integer.MIN_VALUE;

        if (firstIndex <= lastIndex) {
            for (int i = 0; i < size; i++) {
                if (collection[i] > max) {
                    max = collection[i];
                }
            }
        } else {
            for (int i = firstIndex; i < capacity; i++) {
                if (collection[i] > max) {
                    max = collection[i];
                }
            }

            for (int i = 0; i <= lastIndex; i++) {
                if (collection[i] > max) {
                    max = collection[i];
                }
            }
        }
        return max;
    }

    /**
     * Получение очереди элементов
     * @return Полная очередь элементов
     * */
    @Override
    public int[] getCollection() {
        if (size == 0) {
            return null;
        }
        int[] newCollection = new int[size];

        if (firstIndex <= lastIndex) {
            for (int i = 0, j = firstIndex; j <= lastIndex; i++, j++) {
                newCollection[i] = collection[j];
            }
        } else {
            int i = 0;

            for (int j = firstIndex; j < capacity; i++, j++) {
                newCollection[i] = collection[j];
            }

            for (int j = 0; j <= lastIndex; i++, j++) {
                newCollection[i] = collection[j];
            }
        }
        return newCollection;
    }

    /**
     * Получение элементов очереди от начального индекса до конца очереди
     * @return Новый массив элементов от начального индекса до конца очереди
     * */
    @Override
    public int[] getCollection(int startIndex) {
        if (size == 0) {
            return null;
        }
        checkIndex(startIndex);
        startIndex = transformIndex(startIndex);

        int newSize = 0;
        int[] newCollection;

        if (firstIndex <= lastIndex) {
            newSize = lastIndex - startIndex + 1;
            newCollection = new int[newSize];

            for (int i = 0, j = startIndex; j <= lastIndex; i++, j++) {
                newCollection[i] = collection[j];
            }
        } else {
            newSize = (capacity - startIndex) + lastIndex;
            newCollection = new int[newSize];
            int i = 0;

            for (int j = startIndex; j < capacity; i++, j++) {
                newCollection[i] = collection[j];
            }

            for (int j = 0; j <= lastIndex; i++, j++) {
                newCollection[i] = collection[j];
            }
        }
        return newCollection;
    }

    /**
     * Получение элементов очереди от начального индекса до конечного индекса
     * @return Новый массив элементов от начального индекса до конечного индекса
     * */
    @Override
    public int[] getCollection(int startIndex,int endIndex) {
        if (size == 0) {
            return null;
        }
        checkIndex(startIndex);
        checkIndex(endIndex);

        int newStartIndex = transformIndex(startIndex);
        int newEndIndex = transformIndex(endIndex);
        checkIndexes(newStartIndex, newEndIndex, startIndex, endIndex);

        int newSize = 0;
        int[] newCollection;

        if (firstIndex <= lastIndex) {
            newSize = newEndIndex - newStartIndex + 1;
            newCollection = new int[newSize];

            for (int i = 0, j = startIndex; j <= newEndIndex; i++, j++) {
                newCollection[i] = collection[j];
            }
        } else {
            newSize = (capacity - newStartIndex) + newEndIndex;
            newCollection = new int[newSize];
            int i = 0;

            for (int j = newStartIndex; j < capacity; i++, j++) {
                newCollection[i] = collection[j];
            }

            for (int j = 0; j <= newEndIndex; i++, j++) {
                newCollection[i] = collection[j];
            }
        }
        return newCollection;
    }


    // ADD - добавление данных
    /**
     * Добавление элемента в конец очереди
     * Копирует метод {@link IntQueue#addLast(int)}
     * @param value Значение добавляемого элемента
     * **/
    @Override
    public void add(int value) {
        addLast(value);
    }

    /**
     * Добавление элемента в конец очереди
     * @param value Значение добавляемого элемента
     * @exception ArrayStoreException Ошибка переполнения очереди
     * **/
    public void addLast(int value) {
        if (size == capacity) {
            throw new ArrayStoreException("Очередь заполнена. Добавление нового элемента невозможно");
        }

        if (size == 1) {
            firstIndex = lastIndex = 0;
        } else {
            lastIndex++;
        }

        if (lastIndex == capacity) {
            lastIndex = 0;
        }
        collection[lastIndex] = value;
        size++;
    }

    /**
     * Добавление элемента в конец очереди.
     * Копирует метод {@link IntQueue#offerLast(int)}
     * @param value Значение добавляемого элемента
     * @return true - если новый элемент добавлен, иначе - false
     * **/
    @Override
    public boolean offer(int value) {
        return offerLast(value);
    }

    /**
     * Добавление элемента в конец очереди
     * @param value Значение добавляемого элемента
     * @return true - если новый элемент добавлен, иначе - false
     * **/
    public boolean offerLast(int value) {
        if (size == capacity) {
            return false;
        }

        if (size == 1) {
            firstIndex = lastIndex = 0;
        } else {
            lastIndex++;
        }

        if (lastIndex == capacity) {
            lastIndex = 0;
        }

        collection[lastIndex] = value;
        size++;
        return true;
    }


    // SETTERS - изменение данных
    /**
     * Установка значения первому элементу очереди
     * @param value Новое значение первого элемента очереди
     * @exception NoSuchElementException Ошибка изменения элемента в пустой очереди
     **/
    public void setFirst(int value) {
        if (size == 0) {
            throw new NoSuchElementException("Очередь пуста. Элементов для изменения значений нет");
        }
        collection[firstIndex] = value;
    }

    /**
     * Установка значения последнему элементу очереди
     * @param value Новое значение последнего элемента очереди
     * @exception NoSuchElementException Ошибка изменения элемента в пустой очереди
     **/
    public void setLast(int value) {
        if (size == 0) {
            throw new NoSuchElementException("Очередь пуста. Элементов для изменения значений нет");
        }
        collection[lastIndex] = value;
    }


    // REMOVE - удаление данных
    /**
     * Удаление элемента двусторонней очереди по указанному индексу с возвращением удалённого элемента
     * @param index Индекс удаляемого элемента
     *              (положительный - с начала двусторонней очереди, отрицательный - с конца двусторонней очереди)
     * @return Удалённый элемент двусторонней очереди
     * @exception NoSuchElementException Ошибка удаления элемента пустой двусторонней очереди
     * */
    @Override
    public int remove(int index) {
        if (size == 0) {
            throw new NoSuchElementException("В двусторонней очереди отсутствуют элементы");
        }
        checkIndex(index);
        index = transformIndex(index);
        index += firstIndex;
        int value = 0;

        if (firstIndex <= lastIndex) {
            value = collection[index];

            for (int i = index; i <= lastIndex; i++) {
                collection[i] = collection[i + 1];
            }
        } else if (index < capacity) {
            value = collection[index];

            for (int i = index; i < capacity - 1; i++) {
                collection[i] = collection[i + 1];
            }

            collection[capacity - 1] = collection[0];

            for (int i = 0; i < lastIndex; i++) {
                collection[i] = collection[i + 1];
            }
        } else {
            index -= capacity;
            value = collection[index];

            for (int i = index; i <= lastIndex; i++) {
                collection[i] = collection[i + 1];
            }
        }

        collection[lastIndex] = 0;
        lastIndex--;
        size--;

        if (lastIndex == -1) {
            lastIndex = capacity - 1;
        }

        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
        }
        return value;
    }

    /**
     * Удаление элемента двусторонней очереди по указанному индексу с возвращением удалённого элемента
     * @param index Индекс удаляемого элемента
     *              (положительный - с начала двусторонней очереди, отрицательный - с конца двусторонней очереди)
     * @return Удалённый элемент двусторонней очереди типа {@link Integer}
     * */
    @Override
    public Integer poll(int index) {
        if (size == 0) {
            throw new NoSuchElementException("В двусторонней очереди отсутствуют элементы");
        }
        checkIndex(index);
        index = transformIndex(index);
        index += firstIndex;
        int value = 0;

        if (firstIndex <= lastIndex) {
            value = collection[index];

            for (int i = index; i <= lastIndex; i++) {
                collection[i] = collection[i + 1];
            }
        } else if (index < capacity) {
            value = collection[index];

            for (int i = index; i < capacity - 1; i++) {
                collection[i] = collection[i + 1];
            }
            collection[capacity - 1] = collection[0];

            for (int i = 0; i <lastIndex; i++) {
                collection[i] = collection[i + 1];
            }
        } else {
            index -= capacity;
            value = collection[index];

            for (int i = index; i <= lastIndex; i++) {
                collection[i] = collection[i + 1];
            }
        }

        collection[lastIndex] = 0;
        lastIndex--;
        size--;

        if (lastIndex == -1) {
            lastIndex = capacity - 1;
        }

        if (size == 1) {
            firstIndex = 0;
            lastIndex = 0;
        }
        return value;
    }

    /**
     * Удаление элемента из начала очереди с возвращением удалённого элемента
     * @return Удалённый элемент очереди
     * @exception NoSuchElementException Ошибка удаления элемента пустой очереди
     * **/
    public int removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Очередь пуста. Удаление первого элемента невозможно");
        }
        int value = collection[firstIndex];

        collection[firstIndex] = 0;
        size--;

        if (size == 1) {
            firstIndex = lastIndex = 0;
        } else {
            firstIndex++;

            if (firstIndex == capacity) {
                firstIndex = 0;
            }
        }
        return value;
    }

    /**
     * Удаление элемента из начала очереди с возвращением удалённого элемента
     * (если очередь пуста, возвращается значение null)
     * @return Удалённый элемент очереди типа {@link Integer}
     * **/
    public Integer pollFirst() {
        if (size == 0) {
            return null;
        }
        int value = collection[firstIndex];

        collection[firstIndex] = 0;
        size--;

        if (size == 1) {
            firstIndex = lastIndex = 0;
        } else {
            firstIndex++;

            if (firstIndex == capacity) {
                firstIndex = 0;
            }
        }
        return value;
    }


    // TO_STRING - строковое преобразование данных
    /**
     * Вывод содержимого элементов очереди в виде строки
     * @return Строка, состоящая из элементов очереди, разделённых пробелом
     * */
    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        result.append(collection[firstIndex]);
        int index = firstIndex + 1;

        if (firstIndex <= lastIndex) {
            while (index <= lastIndex) {
                result.append(" ").append(collection[index]);
                index++;
            }
        } else {
            while (index < capacity) {
                result.append(" ").append(collection[index]);
                index++;
            }
            index = 0;

            while (index <= lastIndex) {
                result.append(" ").append(collection[index]);
                index++;
            }
        }
        return result.toString();
    }

    /**
     * Вывод содержимого элементов очереди, вместе с их индексами
     * @return Строка, состоящая из элементов очереди, включая их индексы
     * */
    public String deepToString() {
        // ANSI escape codes
        final String RESET = "\u001B[0m";
        final String BLACK = "\u001b[30m";
        final String BG_WHITE = "\u001b[47m";

        int spaceSize = capacity - size;

        StringBuilder result = new StringBuilder();

        if (firstIndex <= lastIndex) {
            int index = 0;
            int queueIndex = 0;

            while (index < firstIndex) {
                result.append("[-]=0 ");
                index++;
            }

            while (index <= lastIndex) {
                result.append(BLACK + BG_WHITE);
                result.append("[").append(queueIndex).append("]=").append(collection[index]).append(" ");
                result.append(RESET);
                index++;
                queueIndex++;
            }

            while (index < capacity) {
                result.append("[-]=0 ");
                index++;
            }
        } else {
            int index = 0;
            int queueIndex = size - (lastIndex + 1);

            while (index <= lastIndex) {
                result.append(BLACK + BG_WHITE);
                result.append("[").append(queueIndex).append("]=").append(collection[index]).append(" ");
                result.append(RESET);
                index++;
                queueIndex++;
            }

            while (index < firstIndex) {
                result.append("[-]=0 ");
                index++;
            }

            queueIndex = 0;

            while (index < capacity) {
                result.append(BLACK + BG_WHITE);
                result.append("[").append(queueIndex).append("]=").append(collection[index]).append(" ");
                result.append(RESET);
                index++;
                queueIndex++;
            }
        }
        return result.toString();
    }
}
