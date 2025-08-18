package collections;

import java.util.NoSuchElementException;

public class IntArray {
    // FIELDS - поля класса
    /**
     * Массив целочисленных значений
     * */
    protected int[] collection;
    /**
     * Вместимость массива
     * */
    protected int capacity;
    /**
     * Количество установленных значений массива
     * */
    protected int size;


    // CONSTRUCTORS - конструкторы класса
    /**
     * Конструктор массива по умолчанию
     * */
    public IntArray() {
        this(16);
    }

    /**
     * Конструктор массива с указанием его вместимости
     * @param capacity Вместимость будущего массива
     * @exception NegativeArraySizeException Ошибка указания отрицательного значения вместимости массива
     * */
    public IntArray(int capacity) {
        if (capacity < 0) {
            throw new NegativeArraySizeException("Вместимость массива не может иметь отрицательное значение");
        }

        this.capacity = capacity;
        collection = new int[capacity];
        size = 0;
    }

    /**
     * Конструктор массива с указанием списка добавляемых значений
     * @param values Список добавляемых значений
     * */
    public IntArray(int... values) {
        size = values.length;
        capacity = (int)(size * 1.5);
        collection = new int[capacity];

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                collection[i] = values[i];
            }
        }
    }

    /**
     * Конструктор массива с указанием его вместимости и списком добавляемых значений
     * @param capacity Вместимость будущего массива
     * @param values Список добавляемых значений
     * @exception NegativeArraySizeException Ошибка указания отрицательного значения вместимости массива
     * */
    public IntArray(int capacity, int... values) {
        if (capacity < 0) {
            throw new NegativeArraySizeException("Вместимость массива не может иметь отрицательное значение");
        }

        size = 0;
        this.capacity = capacity;
        collection = new int[capacity];

        if (capacity > 0) {
            for (int i = 0; i < values.length; i++) {
                // если указанная вместимость массива меньше количества добавляемых значений,
                // то не вошедшие в пределы вместимости массива значения списка игнорируются
                if (i == capacity) break;

                collection[i] = values[i];
                size++;
            }
        }
    }


    // SERVICES - служебные методы
    /**
     * Проверка допустимости индекса
     * @param index Проверяемый индекс
     * @exception ArrayIndexOutOfBoundsException Ошибка указания индекса за пределами вместимости массива
     * @exception ArrayIndexOutOfBoundsException Ошибка отсутствия элемента по указанному индексу
     * */
    protected void checkIndex(int index) {
        if (index == capacity || Math.abs(index) > capacity) {
            throw new ArrayIndexOutOfBoundsException("Индекс " + index + " выходит за пределы вместимости массива");
        }

        if (index == size || Math.abs(index) > size) {
            throw new ArrayIndexOutOfBoundsException("Элемент по индексу" + index + " отсутствует");
        }
    }

    /**
     * Преобразование отрицательного индекса массива в положительное
     * @param index Преобразуемый индекс
     * @return Новое (положительное) значение индекса
     * */
    protected int transformIndex(int index) {
        return (index < 0) ? size + index : index;
    }

    /**
     * Проверка соответствия начального и конечного индексов выборки
     * @param startIndex Вычисленное значение начального индекса массива для выборки
     * @param endIndex Вычисленное значение конечного индекса массива для выборки
     * @param oldStartIndex Изначальное значение начального индекса массива для выборки
     * @param oldEndIndex Изначальное значение конечного индекса массива для выборки
     * */
    protected void checkIndexes(int startIndex, int endIndex, int oldStartIndex, int oldEndIndex) {
        if (startIndex > endIndex) {
            throw new ArrayIndexOutOfBoundsException("Элемент с индексом " + oldStartIndex +
                    " не может стоять после элемента с индексом " + oldEndIndex);
        }
    }


    // GETTERS - получение данных
    /**
     * Получение элемента массива по индексу
     * @param index Индекс возвращаемого элемента
     *              (положительный - с начала массива, отрицательный - с конца массива)
     * @return Элемент массива по указанному индексу
     * @exception NoSuchElementException Ошибка получения элемента из пустого массива
     * */
    public int getElement(int index) {
        if (size == 0) {
            throw new NoSuchElementException("В массиве отсутствуют элементы");
        }
        checkIndex(index);
        index = transformIndex(index);
        return collection[index];
    }

    /**
     * Получение элемента массива по индексу
     * (если массив пуст, возвращается значение null)
     * @param index Индекс возвращаемого элемента
     *              (положительный - с начала массива, отрицательный - с конца массива)
     * @return Элемент массива по указанному индексу типа {@link Integer}
     * */
    public Integer peek(int index) {
        if (size == 0) {
            return null;
        }
        checkIndex(index);
        index = transformIndex(index);
        return collection[index];
    }

    /**
     * Получение минимального значения элементов массива
     * @return Минимальное значение элементов массива типа {@link Integer}
     * */
    public Integer getMin() {
        if (size == 0) {
            return null;
        }
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            if (collection[i] < min) {
                min = collection[i];
            }
        }
        return min;
    }

    /**
     * Получение максимального значения элементов массива
     * @return Максимальное значение элементов массива типа {@link Integer}
     * */
    public Integer getMax() {
        if (size == 0) {
            return null;
        }
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < size; i++) {
            if (collection[i] > max) {
                max = collection[i];
            }
        }
        return max;
    }

    /**
     * Получение массива элементов
     * @return Полный массив элементов
     * */
    public int[] getCollection() {
        if (size == 0) {
            return null;
        }
        int[] newCollection = new int[size];

        for (int i = 0; i < size; i++) {
            newCollection[i] = collection[i];
        }
        return newCollection;
    }

    /**
     * Получение элементов массива от начального индекса до конца массива
     * @return Новый массив элементов от начального индекса до конца массива
     * */
    public int[] getCollection(int startIndex) {
        if (size == 0) {
            return null;
        }
        checkIndex(startIndex);
        startIndex = transformIndex(startIndex);

        int newSize = size - startIndex;
        int[] newCollection = new int[newSize];

        for (int i = 0, j = startIndex; i < newSize; i++, j++) {
            newCollection[i] = collection[j];
        }
        return newCollection;
    }

    /**
     * Получение элементов массива от начального индекса до конечного индекса
     * @return Новый массив элементов от начального индекса до конечного индекса
     * */
    public int[] getCollection(int startIndex,int endIndex) {
        if (size == 0) {
            return null;
        }
        checkIndex(startIndex);
        checkIndex(endIndex);

        int newStartIndex = transformIndex(startIndex);
        int newEndIndex = transformIndex(endIndex);
        checkIndexes(newStartIndex, newEndIndex, startIndex, endIndex);

        int newSize = newEndIndex - newStartIndex + 1;
        int[] newCollection = new int[newSize];

        for (int i = 0, j = newStartIndex; i < newSize; i++, j++) {
            newCollection[i] = collection[j];
        }
        return newCollection;
    }

    /**
     * Получение вместимости массива
     * @return Вместимость массива
     * */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Получение количества элементов массива
     * @return Количество элементов массива
     * */
    public int getSize() {
        return size;
    }


    // ADD - добавление данных
    /**
     * Добавление элемента в массив
     * @param value Значение добавляемого элемента
     * @exception ArrayStoreException Ошибка переполнения массива
     * */
    public void add(int value) {
        if (size == capacity) {
            throw new ArrayStoreException("Массив заполнен. Добавление нового элемента невозможно");
        }
        collection[size] = value;
        size++;
    }

    /**
     * Добавление элемента в массив
     * @param value Значение добавляемого элемента
     * @return true - если новый элемент добавлен в массив, иначе - false
     * */
    public boolean offer(int value) {
        if (size == capacity) {
            return false;
        }
        collection[size] = value;
        size++;
        return true;
    }


    // SETTERS - изменение данных
    /**
     * Установка значения элементу массива по указанному индексу
     * @param index Индекс изменяемого элемента
     *              (положительный - с начала массива, отрицательный - с конца массива)
     * @param value Новое значение изменяемого элемента
     * @exception NoSuchElementException Ошибка изменения элемента пустого массива
     * */
    public void setElement(int index, int value) {
        if (size == 0) {
            throw new NoSuchElementException("В массиве отсутствуют элементы");
        }
        checkIndex(index);
        index = transformIndex(index);
        collection[index] = value;
    }

    /**
     * Установка нового значения всем элементам массива
     * @param value Новое значение элементов массива
     * @exception NoSuchElementException Ошибка изменения элемента пустого массива
     * */
    public void setAll(int value) {
        if (size == 0) {
            throw new NoSuchElementException("В массиве отсутствуют элементы");
        }

        for (int i = 0; i < size; i++) {
            collection[i] = value;
        }
    }


    // REMOVE - удаление данных
    /**
     * Удаление элемента массива по указанному индексу с возвращением удалённого элемента
     * @param index Индекс удаляемого элемента
     *              (положительный - с начала массива, отрицательный - с конца массива)
     * @return Удалённый элемент массива
     * @exception NoSuchElementException Ошибка удаления элемента пустого массива
     * */
    public int remove(int index) {
        if (size == 0) {
            throw new NoSuchElementException("В массиве отсутствуют элементы");
        }
        checkIndex(index);
        index = transformIndex(index);

        int value = collection[index];

        for (int i = index; i < size - 1; i++) {
            collection[i] = collection[i + 1];
        }
        size--;
        collection[size] = 0;
        return value;
    }

    /**
     * Удаление элемента массива по указанному индексу с возвращением удалённого элемента
     * @param index Индекс удаляемого элемента
     *              (положительный - с начала массива, отрицательный - с конца массива)
     * @return Удалённый элемент массива типа {@link Integer}
     * */
    public Integer poll(int index) {
        if (size == 0) {
            return null;
        }
        checkIndex(index);
        index = transformIndex(index);

        int value = collection[index];

        for (int i = index; i < size - 1; i++) {
            collection[i] = collection[i + 1];
        }
        size--;
        collection[size] = 0;
        return value;
    }

    /**
     * Очищение массива элементов
     * @exception NoSuchElementException Ошибка удаления элемента пустого массива
     * */
    public void clear() {
        if (size == 0) {
            throw new NoSuchElementException("В массиве отсутствуют элементы");
        }

        for (int item : collection) {
            item = 0;
        }
        size = 0;
    }


    // CHECK - проверка данных
    /**
     * Проверка наличия элемента в массиве с указанным значением
     * @param value Проверяемое значение
     * @return true - если в массиве имеется элемент с проверяемым значением,
     * иначе - false. Если массив пуст - возвращается null
     * */
    public boolean contains(int value) {
        if (size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (collection[i] == value) {
                return true;
            }
        }
        return false;
    }


    // SEARCH - поиск данных
    /**
     * Получение индекса расположения в массиве элемента с указанным значением
     * @param value Искомое значение
     * @return Индекс расположения элемента массива с указанным значением типа {@link Integer}.
     * Если элемент не найден возвращается -1. Если массив пуст - возвращается null
     * */
    public Integer indexOf(int value) {
        if (size == 0) {
            return null;
        }

        for (int i = 0; i < size; i++) {
            if (collection[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Получение индекса расположения в массиве элемента с указанным значением, начиная с конца массива
     * @param value Искомое значение
     * @return Индекс расположения элемента массива с указанным значением типа {@link Integer},
     * начиная с конца массива. Если элемент не найден возвращается -1. Если массив пуст - возвращается null
     * */
    public Integer lastIndexOf(int value) {
        if (size == 0) {
            return null;
        }

        for (int i = size - 1; i > -1; i--) {
            if (collection[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Количество элементов массива с указанным значением
     * @param value Искомое значение
     * @return Количество элементов массива с указанным значением типа {@link Integer}.
     * Если массив пуст - возвращается null
     * */
    public Integer findCount(int value) {
        if (size == 0) {
            return null;
        }
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (collection[i] == value) {
                count++;
            }
        }
        return count;
    }


    // SORT - сортировка данных
    /**
     * Сортировка элементов массива в порядке увеличения значений
     * @return true - если сортировка прошла успешно, иначе - false
     * */
    public boolean sort() {
        if (size == 0) {
            return false;
        }
        boolean sorted = false;

        for (int i = 0; i < size -1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (collection[i] > collection[j]) {
                    int temp = collection[i];
                    collection[i] = collection[j];
                    collection[j] = temp;
                    sorted = true;
                }
            }
        }
        return sorted;
    }

    /**
     * Сортировка элементов массива в порядке уменьшения значений
     * @return true - если сортировка прошла успешно, иначе - false
     * */
    public boolean reverseSort() {
        if (size == 0) {
            return false;
        }
        boolean sorted = false;

        for (int i = 0; i < size -1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (collection[i] < collection[j]) {
                    int temp = collection[i];
                    collection[i] = collection[j];
                    collection[j] = temp;
                    sorted = true;
                }
            }
        }
        return sorted;
    }


    // REVERSE - перевёртывание данных
    /**
     * Перевёртывание элементов массива
     * @exception NoSuchElementException Попытка перевёртывания элементов пустого массива
     * */
    public void reverse() {
        if (size == 0) {
            throw new NoSuchElementException("В массиве отсутствуют элементы");
        }

        for (int i = 0, j = size - 1; i < size / 2; i++, j--) {
            int temp = collection[i];
            collection[i] = collection[j];
            collection[j] = temp;
        }
    }


    // TRANSFORM - Трансформация данных
    /**
     * Увеличение вместимости массива в значение по умолчанию (в 1,5 раза)
     * */
    public void increaseCapacity() {
        int newCapacity = (int)(capacity * 0.5);
        increaseCapacity(newCapacity);
    }

    /**
     * Увеличение вместимости массива на указанное значение
     * */
    public void increaseCapacity(int capacity) {
        this.capacity += capacity;
        int[] newCollection = new int[this.capacity];

        for (int i = 0; i < size; i++) {
            newCollection[i] = collection[i];
        }
        collection = newCollection;
    }

    /**
     * Перемена значений двух элементов с указанными индексами
     * @param firstIndex Индекс первого элемента массива
     * @param secondIndex Индекс второго элемента массива
     * @exception NoSuchElementException Попытка изменения элементов пустого массива
     * */
    public void transform(int firstIndex, int secondIndex) {
        if (size == 0) {
            throw new NoSuchElementException("В массиве отсутствуют элементы");
        }
        checkIndex(firstIndex);
        checkIndex(secondIndex);

        firstIndex = transformIndex(firstIndex);
        secondIndex = transformIndex(secondIndex);

        int temp = collection[firstIndex];
        collection[firstIndex] = collection[secondIndex];
        collection[secondIndex] = temp;
    }


    // COPY - копирование данных
    /**
     * Копирование массива
     * */
    public int[] copy() {
        int[] newCollection = new int[capacity];

        for (int i = 0; i < size; i++) {
            newCollection[i] = collection[i];
        }
        return newCollection;
    }


    // UNION - объединение данных
    /**
     * Объединение массива с новой коллекцией элементов
     * @param values Новая коллекция элементов
     * @return Новый массив с объединёнными элементами текущего массива с добавляемой коллекцией
     * */
    public int[] concat(int... values) {
        int newSize = size + values.length;
        int[] newCollection = new int[newSize];

        for (int i = 0; i < size; i++) {
            newCollection[i] = collection[i];
        }

        for (int i = size, j = 0; i < newSize; i++, j++) {
            newCollection[i] = values[j];
        }
        return newCollection;
    }

    /**
     * Преобразование элементов массива в строку с указанным разделителем
     * @param separator Строковый разделитель
     * @return Строка из элементов массива, разделённых указанным разделителем
     * */
    public String join(String separator) {
        StringBuilder result = new StringBuilder();
        result.append(collection[0]);

        for (int i = 1; i < size; i++) {
            result.append(separator).append(collection[i]);
        }
        return result.toString();
    }


    // SUM - сложение данных
    /**
     * Сложение значений элементов массива
     * @return Сумма элементов массива типа {@link Integer}
     * */
    public Integer sum() {
        if (size == 0) {
            return null;
        }
        int sum = 0;

        for (int i = 0; i < size; i++) {
            sum += collection[i];
        }
        return sum;
    }


    // TO_STRING - строковое преобразование данных
    /**
     * Вывод содержимого элементов массива в виде строки
     * @return Строка, состоящая из элементов массива, разделённых пробелом
     * */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(collection[0]);

        for (int i = 1; i < size; i++) {
            result.append(" ").append(collection[i]);
        }
        return result.toString();
    }


    // PRINT - вывод коллекции на экран
    /**
     * Отображение массива элементов на экране
     * */
    public void print() {
        if (size > 0) {
            StringBuilder result = new StringBuilder();
            result.append("Array: [0]=").append(collection[0]);

            for (int i = 1; i < capacity; i++) {
                result.append("; [").append(i).append("]=").append(collection[i]);
            }

            System.out.println(result);
        }

        System.out.println("Capacity: " + capacity);
        System.out.println("Size: " + size);
    }
}
