package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sale;
import entities.SaleServices;

public class AnaliseDeVendas1 {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
			
		System.out.print("Entre com o Caminho do Arquivo: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Sale> list = new ArrayList<>();
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				Integer month = Integer.parseInt(fields[0]);
				Integer year = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);
				list.add(new Sale(month, year, seller, items, total));
			
				line = br.readLine();
				
			}
				
			//Comparator<Sale> comp = (s1,s2) -> s1.averagePrice().compareTo(s2.averagePrice());
			
			List<Sale> vendas = list.stream()
					.filter(x -> x.getYear()==2016 )
					.limit(5)
					.sorted((s1,s2) -> s2.averagePrice().compareTo(s1.averagePrice()))
					.collect(Collectors.toList());

			System.out.println();
			System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");
			vendas.forEach(System.out::println);
		
			
			
			SaleServices ss = new SaleServices();
			double sum = ss.filteredSum(list,p -> p.getSeller().equals("Logan"));
			System.out.println();
			System.out.println("Valor total vendido pelo vendedor Logan nos meses de 1 e 7 = "+String.format("%.2f", sum));
			
		}catch(IOException e) {
			System.out.println("Erro: "+e.getMessage());
			
		}
		
		sc.close();
		

	}

}
