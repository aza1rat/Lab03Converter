package my.functions;

public class InfoConverter {
    public double k;
    public String name;

    public InfoConverter(Double koeff, String ed_izm)
    {
        k = koeff;
        name = ed_izm;
    }

    public String toString()
    {
        return name;
    }
}
