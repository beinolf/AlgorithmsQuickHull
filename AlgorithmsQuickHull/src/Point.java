class Point {
    private int xval, yval;

    Point(int x, int y) {
        this.xval = x;
        this.yval = y;
    }

    public int getXval() {
        return xval;
    }

    public int getYval(){
        return yval;
    }

    //Cross Product determination
    public boolean isOnLeft(Point lineFrom, Point lineTo) {
        return ((lineTo.getXval() - lineFrom.getXval())*(this.getYval() - lineFrom.getYval()) > (lineTo.getYval() - lineFrom.getYval())*(this.getXval() - lineFrom.getXval()));
    }

    //test equivalency
    public boolean equals(Point comp) {
        return (this.xval == comp.getXval() && this.yval == comp.getYval());
    }

    //find distance from this point to a line
    public float distFromLine(Point lineFrom, Point lineTo) {
        int lineXdist = lineTo.getXval() - lineFrom.getXval();
        int lineYdist = lineTo.getYval() - lineFrom.getYval();
        int pointXdist = lineFrom.getXval() - this.xval;
        int pointYdists = lineFrom.getYval() - this.yval;
        int dist = (lineXdist * pointXdist) - (lineYdist * pointYdists);
        return Math.abs(dist);
    }
}
