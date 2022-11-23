class CicleQueue{
    private int front, rear, size;
    private boolean flag;
    private String[] data;
    public CicleQueue(int size) {
        front = rear = 0; //처음 시작은 front와 rear은 0부터 시작
        this.size = size;
        data = new String[size]; 
    }
    private boolean is_Empty() {
        return front == rear&& !flag; //front와 rear가 같고 flag가 false면 비어있음 
    }
    private boolean is_Full() {
        return front == rear && flag;// rear가 front 바로 전 위치에 있으면 가득참
    }
    public String AddQue(String data) {
        if (is_Full())
            return "큐 포화 에러"; //큐가 가득차면 가득찼다고 말함
        else {       	
            rear = (rear+1) % size; 
            flag = true; //값이 들어가면 true 
            return this.data[rear] = data;
        }
    }
    public String DelQue() {
        if (is_Empty()) {
        	return "큐 공백 에러";
        }				
        else {
            front = (front+1) % size;
            flag = false; //값이 나가면 false
            return data[front];
        }  
    }
    public String peek() {
    if(is_Empty())
    	return " 큐 공백 에러";
    else
    	return data[(front+1) % size];
    }

    public void show() { 
        System.out.print("전체: ");
        for(int i = front+1; i != (rear+1) % size; i = (i+1) % size) //front + 1 % size 와 rear + 1 % size가 같지 않을때 까지 출력
            System.out.print(data[i] + " ");
    }
}

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int MAX_QUEUE_SIZE = 8;
		CicleQueue a = new CicleQueue(MAX_QUEUE_SIZE);
		//j k l del M N O P del del  q r del s del t u del w 
		System.out.println("추가 "+a.AddQue("J"));
		System.out.println("추가 "+a.AddQue("K"));
		System.out.println("추가 "+a.AddQue("L"));
		System.out.println("peek "+a.peek());
		System.out.println("삭제 "+a.DelQue());
		System.out.println("추가 "+a.AddQue("M"));
		System.out.println("추가 "+a.AddQue("N"));
		System.out.println("추가 "+a.AddQue("O"));
		System.out.println("추가 "+a.AddQue("P"));
		System.out.println("삭제 "+a.DelQue());
		System.out.println("삭제 "+a.DelQue());
		System.out.println("추가 "+a.AddQue("Q"));
		System.out.println("추가 "+a.AddQue("R"));
		System.out.println("삭제 "+a.DelQue());
		System.out.println("추가 "+a.AddQue("S"));
		System.out.println("삭제 "+a.DelQue());
		System.out.println("추가 "+a.AddQue("T"));
		System.out.println("추가 "+a.AddQue("U"));		
		System.out.println("삭제 "+a.DelQue());
		System.out.println("추가 "+a.AddQue("W"));
		a.show();	//총 6번 삭제 하니 P Q R S T U W 가 출력 되어야 한다.
	}
}
