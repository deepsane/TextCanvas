public class Drawer {
    private static Drawer instance;
    private Character[][] canvas = null;
    private int width = 0, height = 0;
    private char FILL = 'x';
    private char DASH = '-';
    private char PIPE = '|';
    private char BLANK = ' ';

    private Drawer() {};

    public static Drawer getInstance() {
        if (instance == null) {
            instance = new Drawer();
        }
        return instance;
    }

    public int getWidth() {
        return width - 2;
    }

    public void setWidth(int width) {
        assert (width > 0);
        this.width = width + 2;
    }

    public int getHeight() {
        return height - 2;
    }

    public void setHeight(int height) {
        assert (height > 0);
        this.height = height + 2;
    }

    public Character[][] createCanvas(int originalWidth, int originalHeight) {
        this.width = originalWidth + 2;
        this.height = originalHeight + 2;
        canvas = new Character[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || i == width - 1) {
                    canvas[i][j] = DASH;
                } else if (j == 0 || j == height - 1) {
                    canvas[i][j] = PIPE;
                } else {
                    canvas[i][j] = BLANK;
                }
            }
        }
        return canvas;
    }

    public Character[][] drawRectangle(int x1, int y1, int x2, int y2) {
        if (isInvalidInput(x1, y1, x2, y2)) {
            return null;
        }
        drawLine(x1, y1, x1, y2, true);
        drawLine(x2, y1, x2, y2, true);
        drawLine(x1, y1, x2, y1, true);
        drawLine(x1, y2, x2, y2, true);
        return canvas;
    }

    private boolean isInvalidInput(int x1, int y1, int x2, int y2) {
        return this.width == 0 || this.height == 0 ||
               x1 < 1 || x1 > width - 1 || x2 < 1 || x2 > width - 1 ||
               y1 < 1 || y1 > height - 1 || y2 < 1 || y2 > height - 1;
    }

    public Character[][] drawLine(int x1, int y1, int x2, int y2, boolean isPartOfRectangle) {
        if (isInvalidInput(x1, y1, x2, y2)) {
            return null;
        }
        if (x1 == x2) {
            return drawHorizontalLine(x1, y1, y2, isPartOfRectangle);
        } else if (y1 == y2) {
            return drawVerticalLine(y1, x1, x2, isPartOfRectangle);
        }
        return null;
    }

    public Character[][] drawHorizontalLine(int x, int y1, int y2, boolean isPartOfRectangle) {
        for (int j = y1; j <= y2; j++) {
            canvas[x][j] = FILL;
        }
        return !isPartOfRectangle ? canvas : null;
    }

    public Character[][] drawVerticalLine(int y, int x1, int x2, boolean isPartOfRectangle) {
        for (int i = x1; i <= x2; i++) {
            canvas[i][y] = FILL;
        }
        return !isPartOfRectangle ? canvas : null;
    }

    public Character[][] getCanvas() {
        if (canvas == null) {
            createCanvas(width, height);
        }
        return canvas;
    }

    public void setCanvas(Character[][] canvas) {
        if (canvas != null) {
            this.canvas = canvas;
            this.width = canvas.length;
            this.height = canvas[0].length;
        }
    }

    public void reset() {
        this.canvas = null;
        this.width = 0;
        this.height = 0;
    }
}
