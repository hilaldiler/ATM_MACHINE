package Atm;
import java.util.ArrayList;
import java.util.Scanner;

public class Atm {
	// Atm yi user kullanacagý ýcýn user nesnesý burada olusturuldu. (atm user ile varoluyor)
	private User user;
	private ArrayList<User> users = new ArrayList(); 
	int userIndex;

	public Atm(){
		//ArrayList kayýtlý kullanýcýlar olusturuldu...
		users.add(new User("111",1234, 200));
		users.add(new User("222",1235, 500));
		users.add(new User("333",1236,1000));
		
		
		
		
	}
	private boolean isLogin = false; // Hesaba giriþ
	
	



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
		
		while(!hesapNoDogru) {//Hesap No true olmadýgý sürece dönecek...
			/////  HESAP NUMARASI KONTROLU /////
			System.out.println("HESAP NO: ");
			Scanner inputcard = new Scanner(System.in);
			String accountNumber = inputcard.nextLine();
			
			for(int i = 0; i < users.size(); i++) {//ArrayListteki tüm kullanýcýlarý dolaþýyor
				if(users.get(i).getAccountNumber().equals(accountNumber)) { //Herhangibiriyle eþleþtiðinde,
					hesapNoDogru = true;
					userIndex = i;//Hangi indexdeki hesap no ile eþleþtiyse onda devam edicek...
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
		
		while(hesapNoDogru && !parolaDogru) { //Hesap no doðru ancak parola yanlýþ girildiði sürece dönecek...
			/////  SIFRE KONTROLU /////
			 System.out.println("PAROLA: ");
			 Scanner inputpass = new Scanner(System.in);
			 int password = inputpass.nextInt();
			 
			 for(int i = 0; i < users.size(); i++) {//ArrayListteki kullanýcýlarý dolasýyor
					if(users.get(userIndex).getPassword() == password) {// Hesap No ile uyuþan parolayý kontrol edicek,doðru girildiyse,
						parolaDogru = true; 
						isLogin = true;//Giris yapýlýr.
						break;
					}
					else {//Parola dogru deðil ise,
						parolaDogru = false;
					}
			 }
			 
			 if(!parolaDogru) { //parolaDogru = false ise,
					System.out.println("PAROLA HATALI , Tekrar Deneyin");
			 }
		}
	}
		
	
	public void withdrawal() {  //Hesaptan para cekme
		
		 if(isLogin) { //Giris yapýldýysa
			System.out.println("CEKÝLECEK MÝKTAR: ");
			Scanner input = new Scanner(System.in);
			double amount = input.nextDouble();
			
			if (amount < users.get(userIndex).getAmount()) {//Cekilmek istenen miktar bakiyeden kücük ise

				users.get(userIndex).setAmount(users.get(userIndex).getAmount() - amount);//Cekilen para bakiyeden azaltýlýr.
				System.out.println("Kalan Bakiye: " + users.get(userIndex).getAmount());
			}else 
				System.out.println("Bakiye yetersiz !");// bakiyeden daha büyük miktar istendiði zaman
			
			}

		
	}
	
	

	public void cashDeposit() { //Para Yatýrma

		if (isLogin) {

	
		System.out.println("YATIRILACAK MÝKTAR: ");
		Scanner input  = new Scanner(System.in);
		double amount = input.nextDouble();
		
		this.users.get(userIndex).setAmount(this.users.get(userIndex).getAmount() + amount);
		System.out.println("Hesaba Para Yatýrýldý...\nGüncel Bakiye: " + users.get(userIndex).getAmount());
		
	}
				
	}
	
	
		
	public void reference() {//Hesaptan hesaba havale 
		
		boolean gonderilecekHesap = false;
		int gonderilecekHesapIndex = 0;
		
		while(!gonderilecekHesap) {
		System.out.println("Havale yapacagýnýz  kisinin hesap numarasý: ");
		Scanner input = new Scanner(System.in);
		String accountNo = input.nextLine();
		
			for(int i = 0; i < users.size(); i++) { //Kullanýcýlarý dolasýyor...
				if(accountNo.equals(users.get(i).getAccountNumber()) && 
						Integer.parseInt(accountNo) != Integer.parseInt(users.get(userIndex).getAccountNumber())) {
					///Integer.ParseInt() , String yöntemi argümanýnýn bir Tamsayý nesnesine ayrýþtýrýlmasýnda kullanýlýr.///
		///Girilen hesap numarasý kayýtlý kullanýcýlardan biriyle eþleþiyorsa ve girilen kendi hesap numarasý deðilse///	
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
		
		System.out.println("Göndermek istediðiniz miktarý giriniz: ");
		Scanner reference = new Scanner(System.in);
		double amount = reference.nextDouble();
		if (amount < this.users.get(userIndex).getAmount()) {
			
			System.out.println("Hesaba para  göderildi");
			this.users.get(userIndex).setAmount(this.users.get(userIndex).getAmount() - amount);
			System.out.println("Güncel Bakiye: " + users.get(userIndex).getAmount());

			this.users.get(gonderilecekHesapIndex).setAmount(this.users.get(gonderilecekHesapIndex).getAmount() + amount);
			System.out.println("Göderilen Hesaptaki Yeni Bakiye: " + users.get(gonderilecekHesapIndex).getAmount());
			//Havale isleminin ise yaradýgýný kontrol etmek icin ekrana yazdýrdýk//
			
			
		}else 
			System.out.println("Bakiye yetersiz !");// bakiyeden daha büyük miktar istendiði zaman
		}
		
		
		
		
		
		
	
	
	
	public void currentBalance() { ///Güncel Bakiye sorgulama
		
		System.out.println("BAKIYE: " + this.users.get(userIndex).getAmount());
		

	}
	
	public void changePassword() {
	
		Scanner input  = new Scanner(System.in);
		System.out.println("Eski Parola: ");
		int password = input.nextInt();
		
		if(password == this.users.get(userIndex).getPassword()) {//Girilen,kullanýcýnýn parolasýyla uyusuyor ise,
		
		Scanner newinput  = new Scanner(System.in);																																				
		System.out.println("Yeni Parola: ");
		int newpassword = newinput.nextInt();
		this.users.get(userIndex).setPassword(newpassword);//Parola güncellendi...
		System.out.println("Parolanýz Degistirildi");
		
		} else
			System.out.println("Gecerli Parolayý hatalý girdiniz !");
		
		
	}
	
	public void logout() {
		isLogin = false;
		System.out.println("CIKIS YAPILDI");
	}
	
}