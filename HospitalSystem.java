import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//------------------------------------------------------
// HASTA CLASS
//------------------------------------------------------
class Hasta {
    String ad;
    int kritik;
    String sikayet;
    boolean islenen; // false = işlenmedi, true = işlendi
}

//------------------------------------------------------
// 1) QUEUE → Hastaların giriş sırasını tutar
//------------------------------------------------------
class Queue {
    static final int MAX_HASTA = 100;
    Hasta[] data = new Hasta[MAX_HASTA];
    int front = 0, rear = 0;

    boolean push(Hasta h) {
        if ((rear + 1) % MAX_HASTA == front)
            return false; // dolu
        data[rear] = h;
        rear = (rear + 1) % MAX_HASTA;
        return true;
    }

    Hasta get(int index) {
        return data[index];
    }
}

//------------------------------------------------------
// 2) HEAP → Kritik seviyeye göre sıralama
//------------------------------------------------------
class Heap {
    int[] heap = new int[Queue.MAX_HASTA]; // Queue indexleri
    int size = 0;

    int compare(Hasta a, Hasta b) {
        return a.kritik - b.kritik;
    }

    void heapifyUp(int i, Queue q) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (compare(q.get(heap[i]), q.get(heap[p])) >= 0)
                break;

            int tmp = heap[i];
            heap[i] = heap[p];
            heap[p] = tmp;

            i = p;
        }
    }

    void push(int queueIndex, Queue q) {
        heap[size] = queueIndex;
        heapifyUp(size, q);
        size++;
    }

    void heapifyDown(int i, Queue q) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size &&
                compare(q.get(heap[left]), q.get(heap[smallest])) < 0)
                smallest = left;

            if (right < size &&
                compare(q.get(heap[right]), q.get(heap[smallest])) < 0)
                smallest = right;

            if (smallest == i)
                break;

            int tmp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = tmp;

            i = smallest;
        }
    }

    int pop(Queue q) {
        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0, q);
        return root;
    }
}

//------------------------------------------------------
// 3) LINKED LIST → Listeleme için
//------------------------------------------------------
class HastaList {
    ArrayList<Hasta> list = new ArrayList<>();

    void add(Hasta h) {
        list.add(h);
    }

    void showSorted() {
        if (list.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        Collections.sort(list, Comparator.comparingInt(h -> h.kritik));

        int i = 1;
        for (Hasta h : list) {
            System.out.println(i++ + ") " +
                    h.ad + " - Critical: " + h.kritik +
                    " - (" + h.sikayet + ") " +
                    (h.islenen ? "PROCESSED" : "UNPROCESSED"));
        }
    }
}

//------------------------------------------------------
// MAIN
//------------------------------------------------------
public class HospitalSystem {

    static final String[] sikayetler = {
            "Chest pain", "Shortness of breath", "Fainting",
            "Severe headache", "Bleeding", "Fever", "Nausea",
            "Dizziness", "Fracture", "Others"
    };

    static final int[] kritikSeviyeler = {
            1,1,1,2,2,3,3,4,4,5
    };

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        Queue q = new Queue();
        Heap heap = new Heap();
        HastaList list = new HastaList();

        while (true) {
            System.out.println("\n1- Add Patient");
            System.out.println("2- Process Patient");
            System.out.println("3- List Patients");
            System.out.println("4- Exit");
            System.out.print("Choice: ");

            String secim = sc.nextLine();

            // ------------------------------------
            // HASTA EKLE
            // ------------------------------------
            if (secim.equals("1")) {
                Hasta h = new Hasta();

                System.out.print("Patient Name: ");
                h.ad = sc.nextLine();

                for (int i = 0; i < sikayetler.length; i++) {
                    System.out.println((i + 1) + ") " + sikayetler[i]);
                }

                int s;
                do {
                    System.out.print("Select Complaint: ");
                    s = Integer.parseInt(sc.nextLine());
                } while (s < 1 || s > 10);

                h.sikayet = sikayetler[s - 1];
                h.kritik = kritikSeviyeler[s - 1];
                h.islenen = false;

                int idx = q.rear;
                q.push(h);
                heap.push(idx, q);
                list.add(h);

                System.out.println("Patient added!");
            }

            // ------------------------------------
            // HASTA İŞLE
            // ------------------------------------
            else if (secim.equals("2")) {
                if (heap.size == 0) {
                    System.out.println("No patients available.");
                    continue;
                }

                int idx = heap.pop(q);
                Hasta h = q.get(idx);
                h.islenen = true;

                System.out.println("Processed Patient: " +
                        h.ad + " (" + h.sikayet + ") Critical: " + h.kritik);

                Thread.sleep(1000);
            }

            // ------------------------------------
            // HASTA LİSTESİ
            // ------------------------------------
            else if (secim.equals("3")) {
                list.showSorted();
            }

            // ------------------------------------
            // ÇIKIŞ
            // ------------------------------------
            else if (secim.equals("4")) {
                break;
            }

            else {
                System.out.println("Invalid Choice!");
            }
        }

        sc.close();
    }
}
