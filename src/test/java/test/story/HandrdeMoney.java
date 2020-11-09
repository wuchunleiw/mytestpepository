package test.story;

public class HandrdeMoney {

	public static void main(String[] args) {
		
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				for(int m=0;m<100;m++) {
					int num = i+j+m;
					int money = 5*i+3*j+m/3;
					if((num==100)&&(money==100)&&m%3==0) {
						System.out.println("公鸡："+i+"个，母鸡："+j+"个，小鸡："+m+"个");
					}
				}
			}
		}
		
		for(int i=0;i<20;i++) {
			for(int j=0;j<20;j++) {
				for(int m=0;m<20;m++) {
					for(int n=0;n<20;n++) {
						for(int k=0;k<20;k++) {
							int jia = i*2+j;
							int yi = j*3+m;
							int bing = m*4+n;
							int ding = n*5+k;
							int wu = k*6+i;
							if(jia==yi&&yi==bing&&bing==ding&&ding==wu) {
								System.out.println("甲:"+i+",乙:"+j+",丙:"+m+",丁"+n+",戊:"+k);
								System.out.println("绳子长："+jia);
							}
						}
					}
					
				}
			}
		}
		
		
	}
	
	
}
