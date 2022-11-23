package DynamicStorageManagement;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		System.out.println("[기존 메모리 공간]");
		LinkedList<Node> nodes = new LinkedList<Node>();
		nodes.add(new Node(1,7,1));     //값 넣는 부분
		nodes.add(new Node(0,12,8)); 
		nodes.add(new Node(1,8,20));
		nodes.add(new Node(0,18,28));
		nodes.add(new Node(1,20,46));
		nodes.add(new Node(0,15,66));
		System.out.println(nodes);   //링크드리스트 출력
		System.out.println();
		
		LinkedList<Node> nodes1 = new LinkedList<Node>();
		nodes1.add(new Node(1,7,1)); //값 넣는 부분
		nodes1.add(new Node(0,12,8));
		nodes1.add(new Node(1,8,20));
		nodes1.add(new Node(0,18,28));
		nodes1.add(new Node(1,20,46));
		nodes1.add(new Node(0,15,66));
		//p4~p7 넣는 부분
		firstFit(nodes1,new Node(1,14,0));//p4
		firstFit(nodes1,new Node(1,10,0));//p5
		firstFit(nodes1,new Node(1,9,0));//p6
		firstFit(nodes1,new Node(1,8,0));//p7
		System.out.println("[first-fit 메모리 공간]");
		System.out.println(nodes1); //링크드리스트 출력
		System.out.println("[garbage collection]");
		garbageCollection(nodes1);  
		System.out.println(nodes1); //링크드리스트 출력
		System.out.println();
		
		LinkedList<Node> nodes2 = new LinkedList<Node>();	
		nodes2.add(new Node(1,7,1)); //값 넣는 부분
		nodes2.add(new Node(0,12,8));
		nodes2.add(new Node(1,8,20));
		nodes2.add(new Node(0,18,28));
		nodes2.add(new Node(1,20,46));
		nodes2.add(new Node(0,15,66));
		//p4~p7 넣는 부분
		bestFit(nodes2,new Node(1,14,0));//p4
		bestFit(nodes2,new Node(1,10,0));//p5
		bestFit(nodes2,new Node(1,9,0));//p6
		bestFit(nodes2,new Node(1,8,0));//p7
		System.out.println("[best-fit 메모리 공간]");
		System.out.println(nodes2); //링크드리스트 출력
		System.out.println("[garbage collection]");
		garbageCollection(nodes2);
		System.out.println(nodes2); //링크드리스트 출력
		System.out.println();
		
		LinkedList<Node> nodes3 = new LinkedList<Node>();	
		nodes3.add(new Node(1,7,1)); //값 넣는 부분
		nodes3.add(new Node(0,12,8));
		nodes3.add(new Node(1,8,20));
		nodes3.add(new Node(0,18,28));
		nodes3.add(new Node(1,20,46));
		nodes3.add(new Node(0,15,66));
		//p4~p7 넣는 부분 
		worstFit(nodes3,new Node(1,14,0));//p4
		worstFit(nodes3,new Node(1,10,0));//p5
		worstFit(nodes3,new Node(1,9,0));//p6
		worstFit(nodes3,new Node(1,8,0));//p7
		System.out.println("[worst-fit 메모리 공간]");
		System.out.println(nodes3); //링크드리스트 출력
		System.out.println("[garbage collection]");
		garbageCollection(nodes3); 
		System.out.println(nodes3); //링크드리스트 출력
	}
	
	public static LinkedList<Node> firstFit(LinkedList<Node> a,Node b){ //first-Fit 순차적으로 확인하고 크기가 맞는 공간에 넣음
		for(int i = 0;i <a.size();i++) {
			if(a.get(i).p==0 && a.get(i).space >= b.space) { //a(i)의 p가 0이고 a(i)의 공간이 b의 공간보다 크거나 같을때 a(i)에 노드 b를 삽입한다.
				a.add(i,b); 
				if(i==0) { 
					a.get(i).address = 1; //  i가 0이므로 시작주소는 1이다.
					a.get(i+1).space -= a.get(i).space; //i+1의 공간에 i+1의 공간 - i의 공간을 넣는다.
					a.get(i+1).address += a.get(i).space; //i+1의 주소에 i+1의 주소 + i의 공간을 해준다.
				}else {
					a.get(i).address = a.get(i-1).space + a.get(i-1).address;  //삽입할 i의 주소에 i-1의 공간+ i-1의 주소를 준다.
					a.get(i+1).space -= a.get(i).space;  //i+1의 공간에 i+1의 공간 - i의 공간을 넣는다.
					a.get(i+1).address += a.get(i).space; //i+1의 주소에 i+1의 주소 + i의 공간을 해준다.
				}
				break;
			}
		}
		return a;
	}
	public static LinkedList<Node> bestFit(LinkedList<Node> a,Node b){ //best-Fit 적당한 크기에 할당
		int num = 0;
		int space = 1000;
		for(int i = 0;i< a.size(); i++) {
			if(a.get(i).p==0 && a.get(i).space >= b.space) { //p가 0이고 a(i)의 공간이 b공간보다 크거나 같으면 아래 if문으로 이동
				if((a.get(i).space - b.space)<space){ //a(i)의 크기 - b의 크기가 space보다 작으면 space에 a(i)의 크기 - b의 크기를 넣고 num에 i값을 저장
					space = a.get(i).space - b.space;
					num = i;
				}				
			}
		}
		if(a.get(num).space>=b.space) { //num의 초기값을 0으로 줘서 a(0)에 잘못들어가는 걸 방지함
			a.add(num,b);
			if(num==0) {
				a.get(num).address = 1;  //num가 0이므로 시작주소는 1이다.
				a.get(num+1).space -= a.get(num).space; //num+1의 공간에 num+1의 공간 - num의 공간을 넣는다.
				a.get(num+1).address += a.get(num).space; //num+1의 주소에 num+1의 주소 + num의 공간을 해준다.
			}else {
				a.get(num).address = a.get(num-1).space + a.get(num-1).address;  //삽입할 num의 주소에 num-1의 공간 + num-1의 주소를 준다.
				a.get(num+1).space -= a.get(num).space; //num+1의 공간에 num+1의 공간 - num의 공간을 넣는다.
				a.get(num+1).address += a.get(num).space; //num+1의 주소에 num+1의 주소 + num의 공간을 해준다.
			}
		}	
		return a;
	}
	public static LinkedList<Node> worstFit(LinkedList<Node> a,Node b){	 //worst-Fit 가장큰 공간 부터 할당
		int num = 0;
		int space = 0;
		for(int i = 0;i< a.size(); i++) {
			if(a.get(i).p==0 && a.get(i).space >= b.space) { //p가 0이고 a(i)의 공간이 b공간보다 크거나 같으면 아래 if문으로 이동
				if((a.get(i).space - b.space)>space){ //a(i)의 크기 - b의 크기가 space보다 크면 space에 a(i)의 크기 - b의 크기를 넣고 num에 i값을 저장
					space = a.get(i).space - b.space;
					num = i;
				}	
			}
		}
		if(a.get(num).space>=b.space) { //num의 초기값을 0으로 줘서 a(0)에 잘못들어가는 걸 방지함
			a.add(num,b);
			if(num==0) {
				a.get(num).address = 1;  //num가 0이므로 시작주소는 1이다.
				a.get(num+1).space -= a.get(num).space; //num+1의 공간에 num+1의 공간 - num의 공간을 넣는다.
				a.get(num+1).address += a.get(num).space; //num+1의 주소에 num+1의 주소 + num의 공간을 해준다.
			}else {
				a.get(num).address = a.get(num-1).space + a.get(num-1).address;  //삽입할 num의 주소에 num-1의 공간 + num-1의 주소를 준다.
				a.get(num+1).space -= a.get(num).space; //num+1의 공간에 num+1의 공간 - num의 공간을 넣는다.
				a.get(num+1).address += a.get(num).space; //num+1의 주소에 num+1의 주소 + num의 공간을 해준다.
			}
		}		
		return a;
	}
	public static LinkedList<Node> garbageCollection(LinkedList<Node> a){
		int num = 0;
		for(int i = 0; i < a.size(); i++) {
			if(i == a.size()-1) { //링크드리스트의 끝까지 오면 null밖에 없기때문에 break 해줌
				break;
			}
			if(a.get(i).p == 0) { //i의 p가 0이면 
				if(a.get(i+1).p==0) { //i+1의 p가 0이면 두개의 공간을 합치고 i+1의 노드를 제거함
					a.get(i).space += a.get(i+1).space;
					a.remove(i+1);
					i--; //이전으로 돌아가 다시 확인
				}
				else if(a.get(i+1).p==1) { //i의 p가 1이면 i와 i+1의 p의 값을 서로 바꾸고/ i의 주소에 i+1의 공간을 넣고 i+1의 공간에 i의 공간을 넣는다/ i+1의 주소에 i의 공간과 주소를 더한후 넣는다.
					num = a.get(i).p; 
					a.get(i).p = a.get(i+1).p;
					a.get(i+1).p = num;
					num = a.get(i).space;
					a.get(i).space = a.get(i+1).space;
					a.get(i+1).space = num;
					a.get(i+1).address = a.get(i).space +  a.get(i).address;
					i--; //이전으로 돌아가 다시 확인
				}
			}
		}
		return a;
	}
	public static class Node { //노드
		int p; //free이면 0 아니면 1 
		int space; //공간
		int address; //주소
		/**p/공간/주소*/
		public Node(int p, int space, int address) { //생성자
			this.p = p;
			this.space = space;
			this.address = address;
		}
		public String toString() { 
			return String.format("p유무:%d 공간:%dk 시작주소:%dk ->", p,space,address);		
		}
	}
}
