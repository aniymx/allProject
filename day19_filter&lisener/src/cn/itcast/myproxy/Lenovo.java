package cn.itcast.myproxy;

public class Lenovo implements SaleComputer {
    @Override
    public void show() {
        System.out.println("展示电脑....");
    }

    @Override
    public String sale(double money) {
        System.out.println("花了" + money + "元");
        return "联想电脑";
    }

    @Override
    public Lenovo getSal(Object o) {
        System.out.println(o);
        return new Lenovo();
    }
}
