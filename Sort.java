public class Sort
{
    public static void insertionSort (Comparable[] objects)
    {
        for (int index = 1; index < objects.length; index++)
        {
            Comparable key = objects[index];
            int position = index;
            
            while(position > 0 && objects[position-1].compareTo(key) > 0)
            {
                objects[position] = objects[position - 1];
                position--;
            }
            
            objects[position] = key;
        }
    }
}