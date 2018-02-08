package box;

public class BoxController {

	double alpha = 0;

	double[][] nodes = { { -1, -1, -1 }, { -1, -1, 1 }, { -1, 1, -1 }, { -1, 1, 1 }, { 1, -1, -1 }, { 1, -1, 1 },
			{ 1, 1, -1 }, { 1, 1, 1 } };

	double sinX;
	double sinY;
	double cosX;
	double cosY;

	int[][] edges = { { 0, 1 }, { 1, 3 }, { 3, 2 }, { 2, 0 }, { 4, 5 }, { 5, 7 }, { 7, 6 }, { 6, 4 }, { 0, 4 },
			{ 1, 5 }, { 2, 6 }, { 3, 7 } };

	
	public BoxController() {
		
	}

	public void scale(double s) {
		for (int i = 0; i < 8; i++) {
			nodes[i][0] *= s;
			nodes[i][1] *= s;
			nodes[i][2] *= s;
		}
	}

	public double[][] getNodes() {
		return nodes;
	}

	public int[][] getEdges() {
		return edges;
	}

	public void rotate(double angleX, double angleY) {

		sinX = Math.sin(angleX);
		cosX = Math.cos(angleX);
		sinY = Math.sin(angleY);
		cosY = Math.cos(angleY);

		for (int i = 0; i < nodes.length; i++) {
			double x = nodes[i][0];
			double y = nodes[i][1];
			double z = nodes[i][2];

			nodes[i][0] = x * cosX - z * sinX;
			nodes[i][2] = z * cosX + x * sinX;
			z = nodes[i][2];
			nodes[i][1] = y * cosY - z * sinY;
			nodes[i][2] = z * cosY + y * sinY;
		}
	}
}
