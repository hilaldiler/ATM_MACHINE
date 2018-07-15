package Atm;
import java.util.ArrayList;
import java.util.Scanner;

public class Atm {
	// Atm yi user kullanacag� �c�n user nesnes� burada olusturuldu. (atm user ile varoluyor)
	private User user;
	private ArrayList<User> users = new ArrayList(); 
	int userIndex;

	public Atm(){
		//ArrayList kay�tl� kullan�c�lar olusturuldu...
		users.add(new User("111",1234, 200));
		users.add(new User("222",1235, 500));
		users.add(new User("333",1236,1000));
		
		
		
		
	}
	private boolean isLogin = false; // Hesaba giri�
	
	



	public ArrayList<User> getUsers() {
		return users;
	}


	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}


	public boolean isLogin() {
		return isLogin;
	}


	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}


	public Atm(User user) {
		this.user = user;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public void login(User user) { //Hesaba Giris
		
		boolean hesapNoDogru = false;
		boolean parolaDogru = false;
		
		while(!hesapNoDogru) {//Hesap No true olmad�g� s�rece d�necek...
			/////  HESAP NUMARASI KONTROLU /////
			System.out.println("HESAP NO: ");
			Scanner inputcard = new Scanner(System.in);
			String accountNumber = inputcard.nextLine();
			
			for(int i = 0; i < users.size(); i++) {//ArrayListteki t�m kullan�c�lar� dola��yor
				if(users.get(i).getAccountNumber().equals(accountNumber)) { //Herhangibiriyle e�le�ti�inde,
					hesapNoDogru = true;
					userIndex = i;//Hangi indexdeki hesap no ile e�le�tiyse onda devam edicek...
					break;
				}
				else {
					hesapNoDogru = false;
				}
			}
			if(!hesapNoDogru) {
				System.out.println("Hesap Numarasi Hatali, Tekrar Deneyin");
			}
			
		}
		
		while(hesapNoDogru && !parolaDogru) { //Hesap no do�ru ancak parola yanl�� girildi�i s�rece d�necek...
			/////  SIFRE KONTROLU /////
			 System.out.println("PAROLA: ");
			 Scanner inputpass = new Scanner(System.in);
			 int password = inputpass.nextInt();
			 
			 for(int i = 0; i < users.size(); i++) {//ArrayListteki kullan�c�lar� dolas�yor
					if(users.get(userIndex).getPassword() == password) {// Hesap No ile uyu�an parolay� kontrol edicek,do�ru girildiyse,
						parolaDogru = true; 
						isLogin = true;//Giris yap�l�r.
						break;
					}
					else {//Parola dogru de�il ise,
						parolaDogru = false;
					}
			 }
			 
			 if(!parolaDogru) { //parolaDogru = false ise,
					System.out.println("PAROLA HATALI , Tekrar Deneyin");
			 }
		}
	}
		
	
	public void withdrawal() {  //Hesaptan para cekme
		
		 if(isLogin) { //Giris yap�ld�ysa
			System.out.println("CEK�LECEK M�KTAR: ");
			Scanner input = new Scanner(System.in);
			double amount = input.nextDouble();
			
			if (amount < users.get(userIndex).getAmount()) {//Cekilmek istenen miktar bakiyeden k�c�k ise

				users.get(userIndex).setAmount(users.get(userIndex).getAmount() - amount);//Cekilen para bakiyeden azalt�l�r.
				System.out.println("Kalan Bakiye: " + users.get(userIndex).getAmount());
			}else 
				System.out.println("Bakiye yetersiz !");// bakiyeden daha b�y�k miktar istendi�i zaman
			
			}

		
	}
	
	

	public void cashDeposit() { //Para Yat�rma

		if (isLogin) {

	
		System.out.println("YATIRILACAK M�KTAR: ");
		Scanner input  = new Scanner(System.in);
		double amount = input.nextDouble();
		
		this.users.get(userIndex).setAmount(this.users.get(userIndex).getAmount() + amount);
		System.out.println("Hesaba Para Yat�r�ld�...\nG�ncel Bakiye: " + users.get(userIndex).getAmount());
		
	}
				
	}
	
	
		
	public void reference() {//Hesaptan hesaba havale 
		
		boolean gonderilecekHesap = false;
		int gonderilecekHesapIndex = 0;
		
		while(!gonderilecekHesap) {
		System.out.println("Havale yapacag�n�z  kisinin hesap numaras�: ");
		Scanner input = new Scanner(System.in);
		String accountNo = input.nextLine();
		
			for(int i = 0; i < users.size(); i++) { //Kullan�c�lar� dolas�yor...
				if(accountNo.equals(users.get(i).getAccountNumber()) && 
						Integer.parseInt(accountNo) != Integer.parseInt(users.get(userIndex).getAccountNumber())) {
					///Integer.ParseInt() , String y�ntemi arg�man�n�n bir Tamsay� nesnesine ayr��t�r�lmas�nda kullan�l�r.///
		///Girilen hesap numaras� kay�tl� kullan�c�lardan biriyle e�le�iyorsa ve girilen kendi hesap numaras� de�ilse///	
					gonderilecekHesap = true;
					gonderilecekHesapIndex = i;
					break;
				}
				else {
					gonderilecekHesap = false;
					
				}
			}
			if(!gonderilecekHesap) {
				System.out.println("Geecerli bir hesap numarasi girmediniz!");
			}
			
		}
		
		System.out.println("G�ndermek istedi�iniz miktar� giriniz: ");
		Scanner reference = new Scanner(System.in);
		double amount = reference.nextDouble();
		if (amount < this.users.get(userIndex).getAmount()) {
			
			System.out.println("Hesaba para  g�derildi");
			this.users.get(userIndex).setAmount(this.users.get(userIndex).getAmount() - amount);
			System.out.println("G�ncel Bakiye: " + users.get(userIndex).getAmount());

			this.users.get(gonderilecekHesapIndex).setAmount(this.users.get(gonderilecekHesapIndex).getAmount() + amount);
			System.out.println("G�derilen Hesaptaki Yeni Bakiye: " + users.get(gonderilecekHesapIndex).getAmount());
			//Havale isleminin ise yarad�g�n� kontrol etmek icin ekrana yazd�rd�k//
			
			
		}else 
			System.out.println("Bakiye yetersiz !");// bakiyeden daha b�y�k miktar istendi�i zaman
		}
		
		
		
		
		
		
	
	
	
	public void currentBalance() { ///G�ncel Bakiye sorgulama
		
		System.out.println("BAKIYE: " + this.users.get(userIndex).getAmount());
		

	}
	
	public void changePassword() {
	
		Scanner input  = new Scanner(System.in);
		System.out.println("Eski Parola: ");
		int password = input.nextInt();
		
		if(password == this.users.get(userIndex).getPassword()) {//Girilen,kullan�c�n�n parolas�yla uyusuyor ise,
		
		Scanner newinput  = new Scanner(System.in);																																				
		System.out.println("Yeni Parola: ");
		int newpassword = newinput.nextInt();
		this.users.get(userIndex).setPassword(newpassword);//Parola g�ncellendi...
		System.out.println("Parolan�z Degistirildi");
		
		} else
			System.out.println("Gecerli Parolay� hatal� girdiniz !");
		
		
	}
	
	public void logout() {
		isLogin = false;
		System.out.println("CIKIS YAPILDI");
	}
	
}