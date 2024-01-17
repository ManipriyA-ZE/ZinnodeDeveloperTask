import java.util.*;
public class DeveloperTask {

	private static final HashMap<String, Integer> products = null;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		double flat_10_discount=10;
		double bulk_5_discount=5;
		double bulk_10_discount=50;
		double tiered_50_discount=10;
		
		//products and prices
		HashMap<String,Integer> products=new HashMap<>();
		products.put("Product A", 20);
		products.put("Product B", 40);
		products.put("Product C", 50);
		
		//products and quantity
		HashMap<String,Integer> productQuantity=new HashMap<>(); 
		int giftWrap=1;
		for(String s:products.keySet()) {
			System.out.println("enter the quantity: ");
			int quantity=sc.nextInt();
			productQuantity.put(s, quantity);
			System.out.println("is GiftWrap needed ? Yes/No ");
			String isGiftWrap=sc.next().toUpperCase();
			
			if(isGiftWrap.equals("YES")) {
				giftWrap*=quantity;
				
			}
		}
		
		//The product name, quantity & total amount of that product.
		int cartTotal=0;
		for(Map.Entry<String, Integer> entry:productQuantity.entrySet()) {
			String x=entry.getKey();
			int price=products.get(x);
			cartTotal+=entry.getValue()*price;
			System.out.println(entry.getKey()+" quantity is "+entry.getValue()+" totalamount is "+cartTotal);
		}
		
		//discount Amount
		double discountAmount= discountAmount(cartTotal,flat_10_discount,bulk_5_discount,bulk_10_discount,tiered_50_discount,productQuantity);
		
		//discount Applied
		String DiscountApplied=isDiscountApplied(cartTotal,flat_10_discount,bulk_5_discount,bulk_10_discount,tiered_50_discount,productQuantity);
		double shippingFee = 5.0;
        int productsPerPackage = 10;
        double tShippingFee = (productQuantity.values().stream().mapToInt(Integer::intValue).sum() / productsPerPackage) * shippingFee;
        
        //total amount
        double total = cartTotal - discountAmount + giftWrap + tShippingFee;
        
        //output
        System.out.println("subTotal is:" + cartTotal);
        System.out.println("Discount Applied: " + DiscountApplied);
        System.out.println("Discount Amount: " + discountAmount);
        System.out.println("Shipping Fee: " + tShippingFee);
        System.out.println("Gift Wrap Fee: " + giftWrap);
        System.out.println("Total: " + total);
		
		
	
	
	}

	private static String isDiscountApplied(int cartTotal, double flat_10_discount, double bulk_5_discount,
			double bulk_10_discount, double tiered_50_discount, HashMap<String, Integer> productQuantity) {
		if(cartTotal>200) {
			return "flat_10_discount";
			
		}else if(productQuantity.values().stream().anyMatch(q -> q > 10)) {
			return "bulk_5_discount";
		}else if(productQuantity.values().stream().mapToInt(Integer::intValue).sum() > 20) {
			return " bulk_10_discount";
		}else if(productQuantity.values().stream().mapToInt(Integer::intValue).sum() > 30 &&
                productQuantity.values().stream().anyMatch(q -> q > 1)) {
			return "double tiered_50_discount";
		}
		
		return null;
	}

	private static double discountAmount(int cartTotal, double flat_10_discount, double bulk_5_discount,
			double bulk_10_discount, double tiered_50_discount,HashMap<String,Integer>productQuantity) {
		double discountApplied=0;
		if(cartTotal>200) {
			discountApplied=flat_10_discount;
			
		}else if(productQuantity.values().stream().anyMatch(q -> q > 10)) {
			discountApplied=cartTotal*(bulk_5_discount/100);
			
		}else if(productQuantity.values().stream().mapToInt(Integer::intValue).sum() > 20) {
			discountApplied=cartTotal*(bulk_10_discount/100);
		}else if(productQuantity.values().stream().mapToInt(Integer::intValue).sum() > 30 &&
                productQuantity.values().stream().anyMatch(q -> q > 1)) {
			 for (Map.Entry<String, Integer> entry : productQuantity.entrySet()) {
		            String product = entry.getKey();
		            int quantity = entry.getValue();

		            if (quantity > 15) {
		                discountApplied += (quantity - 15) * products.get(products);
		            }
		        }

			   discountApplied=discountApplied * (tiered_50_discount / 100);
			
			
		}
		return discountApplied;
	}
	

	

}
