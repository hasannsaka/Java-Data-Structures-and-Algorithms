import java.util.*;

public class App {
    public static void main(String[] args) {
        // Test with a simple data type (Integer)
        CustomLinkedList<Integer> intList = new CustomLinkedList<>();
        intList.add(5);
        intList.add(3);
        intList.add(8);
        intList.add(3);

        System.out.println("Integer List:");
        intList.printList();

        //test 3.1
        intList.insertAt(2, 19);
        System.out.println("Integer List after 19 inserted at 2:");
        intList.printList();

        // test 3.2
        intList.addFirst(23);
        System.out.println("Integer List after 23 added first:");
        intList.printList();

        // test 3.3 contains
        Integer notInTheList = 11;
        Integer inTheList = 3;
    
        System.out.println(notInTheList + " is" + (intList.contains(notInTheList) ? " " : " not") + " in the list");
        System.out.println(inTheList + " is" + (intList.contains(inTheList) ? " " : " not") + " in the list");

        // test 3.4 
        int index = intList.indexOf(notInTheList);
        System.out.println(notInTheList + " is" + (index >= 0 ? " first occuring at " + index  : " not") + " in the list");
        index = intList.indexOf(inTheList);
        System.out.println(inTheList + " is" + (index >= 0 ? " first occuring at " + index  : " not") + " in the list");

        // test 3.5
        index = intList.lastIndexOf(notInTheList);
        System.out.println(notInTheList + " is" + (index >= 0 ? " first occuring at " + index  : " not") + " in the list");
        index = intList.lastIndexOf(inTheList);
        System.out.println(inTheList + " is" + (index >= 0 ? " last occuring at " + index  : " not") + " in the list");
        
        // test 3.6
        Object [] objectArray = intList.toArray();
        for(Object i : objectArray){
            System.out.print(i.toString() + "->");
        }
    }

    
}
