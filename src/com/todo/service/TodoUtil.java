package com.todo.service;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		 
		String title, category, desc, day, due_date;
		int importance=0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�߰��ϱ�]\n"
				+ "���� > ");
		
		title = sc.next().trim() ;
		sc.nextLine();
		if (l.isDuplicate(title)) {
			System.out.println("�ߺ��� ������ ����� �� �����ϴ�.\n");
			return;
		}

		System.out.print("ī�װ� > ");
		category = sc.next().trim();
		sc.nextLine();
		
		System.out.print("���� > ");
		desc = sc.nextLine();

		System.out.print("�������� > ");
		day = sc.nextLine();
		
		System.out.print("�������� > ");
		due_date = sc.next().trim();

		System.out.print("�߿䵵 > ");
		importance = sc.nextInt();
		
		TodoItem t = new TodoItem(title, category, desc, day, due_date, importance);
		if(l.addItem(t)>0)
			System.out.println("�߰��Ǿ����ϴ�.\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�����ϱ�]\n"
				+ "��ȣ > ");
		int index = sc.nextInt();
		sc.nextLine();
		
		if(l.deleteItem(index)>0)
			System.out.println("�����Ǿ����ϴ�.\n");
	}
	
	public static void deleteItemAll(TodoList l) {
		for(TodoItem item : l.getList()) {
			int index = item.getId();
			l.deleteItem(index);
		}
		System.out.println("��� �׸��� �����Ǿ����ϴ�.\n");
	}
	
	public static void deleteItemCompleted(TodoList l) {
		for(TodoItem item : l.getList()) {
			int index = item.getId();
			int is_completed = item.getIs_completed();
			if(is_completed==1)
				l.deleteItem(index);
		}
		System.out.println("�Ϸ�� �׸���� �����߽��ϴ�.\n");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�����ϱ�]\n"
				+ "��ȣ > ");
		int index = sc.nextInt();
		
		System.out.print("���ο� ���� > ");
		String new_title = sc.next().trim();
		sc.nextLine();
		
		System.out.print("���ο� ī�װ� > ");
		String new_category = sc.next().trim();
		sc.nextLine();
		
		System.out.print("���ο� ���� > ");
		String new_description = sc.nextLine();
		
		System.out.print("���ο� �������� > ");
		String new_due_day = sc.next().trim();
		sc.nextLine();
		
		System.out.print("���ο� �������� > ");
		String new_due_date = sc.next().trim();
		sc.nextLine();
		
		System.out.print("���ο� �߿䵵 > ");
		int new_importance = sc.nextInt();
		
		TodoItem t = new TodoItem(new_title, new_category, new_description, new_due_day, new_due_date, new_importance);
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("�����Ǿ����ϴ�.\n");
	}
	
	public static void findList(TodoList l, String keyword) {
		int count=0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
		System.out.println();
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
		System.out.println();
	}

	public static void listAll(TodoList l) {
		System.out.println("\n[��ü ���, �� " + l.getCount() + "��]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
		System.out.println();
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("\n[��ü ���, �� " + l.getCount() + "��]");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
		System.out.println();
	}
	
	public static void listCateAll(TodoList l) {
		int count=0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println();
		System.out.println("�� " + count + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n");
	}
	
	public static void listCompAll(TodoList l) {
		int count=0;
		for(TodoItem item : l.getCompleted()) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println();
		System.out.println("�� " + count + "���� �׸��� �Ϸ�Ǿ� �ֽ��ϴ�.\n");
	}
	
	public static void compList(TodoList l, int index) {
		int importance=0, is_completed=0;
		String title="";
		String category="";
		String desc="";
		String day="";
		String due_date="";
		String current_date="";
		
		for(TodoItem item : l.getList()) {
			if(item.getId()==index) {
				title = item.getTitle();
				category = item.getCategory();
				desc = item.getDesc();
				day = item.getDay();
				due_date = item.getDue_date();
				current_date = item.getCurrent_date();
				is_completed=item.getIs_completed();
				importance=item.getImportance();
			}
		}
		if(title.equals("")) {System.out.println("�ش��ϴ� �׸��� �����ϴ�.\n");return;}
		else if(is_completed==1) {System.out.println("�̹� �Ϸ�üũ�� �׸��Դϴ�.\n");return;}
		TodoItem t = new TodoItem(index, title, category, desc, day, due_date, current_date, 1, importance);
		if(l.updateItem(t)>0)
			System.out.println("�Ϸ� üũ�Ͽ����ϴ�.\n");
	}
	
	public static void compListMany(TodoList l, String indexes) {
		String []tokens=indexes.split(",");
		
		for(int i=0;i<tokens.length;i++){
			int index = Integer.parseInt(tokens[i]);
			int importance=0;
			String title="";
			String category="";
			String desc="";
			String day="";
			String due_date="";
			String current_date="";
			for(TodoItem item : l.getList()) {
				if(item.getId()==index) {
					title = item.getTitle();
					category = item.getCategory();
					desc = item.getDesc();
					day = item.getDay();
					due_date = item.getDue_date();
					current_date = item.getCurrent_date();
					importance=item.getImportance();
				}
			}
			if(!title.equals("")) {
				TodoItem t = new TodoItem(index, title, category, desc, day, due_date, current_date, 1, importance);
				l.updateItem(t);
			}
		}
		System.out.println("���� �Ϸ� üũ�Ͽ����ϴ�.\n");
	}
	
	public static void compListCancelMany(TodoList l, String indexes) {
		String []tokens=indexes.split(",");
		
		for(int i=0;i<tokens.length;i++){
			int index = Integer.parseInt(tokens[i]);
			int importance=0;
			String title="";
			String category="";
			String desc="";
			String day="";
			String due_date="";
			String current_date="";
			for(TodoItem item : l.getList()) {
				if(item.getId()==index) {
					title = item.getTitle();
					category = item.getCategory();
					desc = item.getDesc();
					day = item.getDay();
					due_date = item.getDue_date();
					current_date = item.getCurrent_date();
					importance=item.getImportance();
				}
			}
			if(!title.equals("")) {
				TodoItem t = new TodoItem(index, title, category, desc, day, due_date, current_date, 0, importance);
				l.updateItem(t);
			}
		}
		System.out.println("���� �Ϸ�üũ ����Ͽ����ϴ�.\n");
	}
	
	public static void compListCancel(TodoList l, int index) {
		int importance=0, is_completed=1;
		String title="";
		String category="";
		String desc="";
		String day="";
		String due_date="";
		String current_date="";
		
		for(TodoItem item : l.getList()) {
			if(item.getId()==index) {
				title = item.getTitle();
				category = item.getCategory();
				desc = item.getDesc();
				day = item.getDay();
				due_date = item.getDue_date();
				current_date = item.getCurrent_date();
				is_completed=item.getIs_completed();
				importance=item.getImportance();
			}
		}
		
		if(title.equals("")) {System.out.println("�ش��ϴ� �׸��� �����ϴ�.\n");return;}
		else if(is_completed==0) {System.out.println("�Ϸ�üũ�� �׸��� �ƴմϴ�.\n");return;}
		TodoItem t = new TodoItem(index, title, category, desc, day, due_date, current_date, 0, importance);
		if(l.updateItem(t)>0)
			System.out.println("�Ϸ� üũ�� ����Ͽ����ϴ�.\n");
	}
}
