package Atm;
import java.util.Scanner;



public class AtmApp {
	public static void main(String[] args) {
		int secim,sec;
		
		Atm atm=new Atm();
		atm.login(atm.getUser());
		
		
		if(atm.isLogin() == true)
			do {

		System.out.println("ISLEMLER: " + "\n==============\n");
		System.out.println("1.Para Cekme \n2.Para Yatýrma \n3.Havale/Eft \n4.Sifre Degistirme \n5.Bakiye Sogulama \n6.Cýkýs");
		Scanner input = new Scanner(System.in);
		secim = input.nextInt();
		
			switch(secim) {
		case 1 : atm.withdrawal();
			break;
		case 2: atm.cashDeposit();
			break;
		case 3: atm.reference();
			break;
		case 4: atm.changePassword();
			break;
		case 5: atm.currentBalance();
			break;
		case 6: atm.logout();
			break;
			default:
				System.out.println("Gecerli secim yapmadýnýz !");
				
		}
			 if(secim == 6) {
				 				 
			    	break;
			 }
			 
			
			 
		System.out.println("Baska islem yapmak istiyor musunuz: \n1:Evet/2:Hayýr ");
		input = new Scanner(System.in);
	    sec = input.nextInt();
	   
		}while(sec == 1);
		
		System.out.println("Ýslem Sonlandýrýldý...");
		
		
	}
}

	
	
	
			
	