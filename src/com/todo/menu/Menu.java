package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<TodoList ���� ��ɾ�>");
        System.out.println("add - �߰��ϱ�");
        System.out.println("del - �����ϱ�");
        System.out.println("edit - �����ϱ�");
        System.out.println("ls - Ȯ���ϱ�");
        System.out.println("ls_cate - ī�װ� Ȯ���ϱ�");
        System.out.println("find Ű���� - �˻��ϱ�");
        System.out.println("find_cate Ű���� - ī�װ� �˻��ϱ�");
        System.out.println("ls_name_asc - �����ϱ�(�����)");
        System.out.println("ls_name_desc - �������ϱ�(�����)");
        System.out.println("ls_date - �����ϱ�(�ð���)");
        System.out.println("ls_date_desc - �������ϱ�(�ð���)");
        System.out.println("import - txt���Ͽ��� ������ ��������");
        System.out.println("comp ��ȣ - �Ϸ� üũ�ϱ�");
        System.out.println("comp_cancel ��ȣ - �Ϸ� üũ ����ϱ�");
        System.out.println("exit - ������");
        System.out.println();
    }
    
    public static void prompt() {
    	System.out.print("��ɾ� > ");
    }
}
