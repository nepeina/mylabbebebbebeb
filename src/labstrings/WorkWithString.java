package labstrings;

import java.io.*;

public class WorkWithString {

	String data;
	String filename;
	String choice;
	String choice2;
	
	RandomAccessFile fio;
	BufferedReader in;
	
	public WorkWithString() throws UnsupportedEncodingException {
		this.in = new BufferedReader(new InputStreamReader(System.in, "Cp1251"));//создание объекта класса BufferedReader
	}	                                                                          //с возможностью ввода 
		                                                                          //данных на русском языке(кодировка 
		                                                                           //"Cp1251")
	public void runConsol() throws IOException{
		while(true) {
			printMenu();
			choice=in.readLine();
			if(choice.compareTo("1")==0) {
				textInfoFile();
			}
			else if(choice.compareTo("2")==0) {
				printRedactMenu();
				choice2=in.readLine();
				if(choice2.compareTo("1")==0) {
					addStart();
				}
				else if(choice2.compareTo("2")==0) {
					addEnd();
				}
				else if(choice2.compareTo("3")==0) {
					addRandom();
				}
			}
			else if(choice.compareTo("3")==0) {
				textFromfile();
			}
			else if(choice.compareTo("4")==0) {
				return;
			}
		}
		
	}
	 
	public void printMenu() {
		System.out.println("Enter your choice:");
		System.out.println("1.Enter text and add it in file");
		System.out.println("2.Edit text in file");
		System.out.println("3.Read text from file and carry out actions on it");
		System.out.println("4.Exit");
	}
	 
	public void textInfoFile() throws IOException{
		System.out.println("Enter the text");
		data=in.readLine();
		System.out.println("Enter the name of the file");
		filename=in.readLine();
		fio = new RandomAccessFile(new File(filename), "rw");
		fio.writeBytes(data); // запись текста в заданный файл
		fio.close();
		System.out.println("Your text is saved");
	}
	
	public void printRedactMenu() throws IOException {
		System.out.println("1.Add text at the beginning of the file");
		System.out.println("2.Add text at the end of file");
		System.out.println("3. Add text at the random position in the file");
	}
	
	public void addStart() throws IOException {
		System.out.println("Enter the name of the file");
		filename=in.readLine();
		fio= new RandomAccessFile(new File(filename), "rw");
		data=fio.readLine(); // чтение информации из заданного файла
		System.out.println("Enter a text for adding");
		String s;
		s=in.readLine();
		fio.seek(0);
		fio.seek(s.length());//переход в конец записанной строки
		fio.writeBytes(data);// запись исходного текста после строки
		fio.close();
		System.out.println("Your string is added in the beginning of the file");
	}
	
	public void addEnd() throws IOException {
		System.out.println("Enter the name of the file");
		filename=in.readLine();
		fio= new RandomAccessFile(new File(filename), "rw");
		data=fio.readLine(); // чтение информации из заданного файла
		System.out.println("Enter a text for adding in the end");
		String s;
		s=in.readLine();
		fio.seek(fio.length());
		fio.writeBytes(s);
		fio.close();
		System.out.println("Your string is added in the end of the file");
	}
	
	public void addRandom() throws IOException {
		System.out.println("Enter the name of the file");
		filename=in.readLine();
		fio= new RandomAccessFile(new File(filename), "rw");
		System.out.println("Enter a text for adding at random place");
		String s;
		s=in.readLine();
		System.out.println("Enter a number of position where you want to add a text");
		int n;
		n=Integer.parseInt(in.readLine());//ввод позиции
		fio.seek(n);
		data=fio.readLine(); //чтение файла начиная с позиции n
		fio.seek(n);
		fio.writeBytes(s);//запись введенной строки с позиции n
		fio.writeBytes(data);//запись прочитанного с позиции n текста после введенной строки
		fio.close();
		System.out.println("Your string is added in the random position of the file");
	}

     public void textFromfile() throws IOException {
    	 System.out.println("Enter the name of the file");
    	 filename=in.readLine();
    	 fio = new RandomAccessFile(new File(filename), "r");
    	 data=fio.readLine();// чтение информации из заданного файла
    	 fio.close();
    	 System.out.println("Информация из файла: " + data);
    	 String[] arrayStr = data.split("\\s");
    	 String first;
    	 String last;
    	 int numb=arrayStr.length;
    	 first=arrayStr[0];
    	 last=arrayStr[numb-1];
    	 for(int i=0;i<=numb;i++) {
    		 if(i==0) {
    			 arrayStr[0]=last;
    		 }
    		 if(i==numb) {
    			arrayStr[numb-1]=first;
    		 }
    	 }
    	 //System.out.println();
    	 
    	 for(int i=0;i<=numb; i++) {
    		 System.out.println(arrayStr[i] + " ");
    	 }
    	 
     }
}

