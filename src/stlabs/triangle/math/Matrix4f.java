package stlabs.triangle.math;

public class Matrix4f 
{
	private float[][] m;
	
	public Matrix4f()
	{
		m = new float[4][4];
	}
	public Matrix4f(float[][] m)
	{
		this.m = m;
	}

	public static Matrix4f identity()
	{
		float[][] m = new float[][] {
				{1,0,0,0},
				{0,1,0,0},
				{0,0,1,0},
				{0,0,0,1}};

		return new Matrix4f(m);
	}
	public static Matrix4f translation(float x, float y, float z)
	{
		float[][] m = new float[][] {
				{1,0,0,x},
				{0,1,0,y},
				{0,0,1,z},
				{0,0,0,1}};

		return new Matrix4f(m);
	}
	public static Matrix4f scale(float x, float y, float z)
	{
		float[][] m = new float[][] {
				{x,0,0,0},
				{0,y,0,0},
				{0,0,z,0},
				{0,0,0,1}};

		return new Matrix4f(m);
	}
	public static Matrix4f perspective(float fov, float aspectRatio, float zNear, float zFar)
	{
		float tanHalfFOV = (float)Math.tan(fov/2);
		float zRange = zNear - zFar;
		
		
		float[][] m = new float[][] {
				{1 / (tanHalfFOV*aspectRatio)		,0									,0						,0							},
				{0									,1 / (tanHalfFOV*aspectRatio)		,0						,0							},
				{0									,0									,(-zNear - zFar)/zRange	,2 * zFar * zNear / zRange	},
				{0									,0									,1						,0							}
			};

		return new Matrix4f(m);
	}
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float zNear, float zFar)
	{
		float width = right - left;
		float height = top - bottom;
		
		float depth = zFar - zNear;
		
		float[][] m = new float[][] {
				{2 / width,0,0,-(right + left)/width},
				{0, 2 / height,0,-(top + bottom)/height},
				{0,0, -2 / depth,-(zFar + zNear)/depth},
				{0,0,0,1}};

		return new Matrix4f(m);
	}
	public static Matrix4f rotation(Vector3f forward, Vector3f up)
	{
		Vector3f f = forward;
		
		Vector3f r = up;
		r = r.cross(f);
		
		Vector3f u = f.cross(r);

		return rotation(f,u,r);
	}
	public static Matrix4f rotation(Vector3f forward, Vector3f up, Vector3f right)
	{
		float[][] m = new float[][] {
				{right.getX()	,right.getY()	,right.getZ()	,0},
				{up.getX()		,up.getY()		,up.getZ()		,0},
				{forward.getX()	,forward.getY()	,forward.getZ()	,0},
				{0				,0				,0				,1}};

		return new Matrix4f(m);
	}
	public static Matrix4f rotation(float x, float y, float z)
	{
		
		final float x_ = (float)Math.toRadians(x);
		final float y_ = (float)Math.toRadians(y);
		final float z_ = (float)Math.toRadians(z);
		
		float[][] rx = new float[][] {
				{1,	0,						0,						0},
				{0,	(float)Math.cos(x_),	-(float)Math.sin(x_),	0},
				{0,	(float)Math.sin(x_),	 (float)Math.cos(x_),	0},
				{0,	0,						0,						1}};
				float[][] ry = new float[][] {
					{(float)Math.cos(y_),	0,	-(float)Math.sin(y_),	0},
					{0,						1,	0,						0},
					{(float)Math.sin(y_),	0,	 (float)Math.cos(y_),	0},
					{0,						0,	0,						1}};
					float[][] rz = new float[][] {
						{(float)Math.cos(z_),	-(float)Math.sin(z_),	0,0},
						{(float)Math.sin(z_),	 (float)Math.cos(z_),	0,0},
						{0,						0,						1,0},
						{0,						0,						0,1}};
		Matrix4f rx_ = new Matrix4f(rx);
		Matrix4f ry_ = new Matrix4f(ry);
		Matrix4f rz_ = new Matrix4f(rz);
		return rz_.mul(ry_.mul(rx_));
	}
	
	public Vector3f transform(Vector3f r)
	{
		return new Vector3f(
				m[0][0] * r.getX() + m[0][1] * r.getY() + m[0][2] * r.getZ() + m[0][3],
				m[1][0] * r.getX() + m[1][1] * r.getY() + m[1][2] * r.getZ() + m[1][3],
				m[2][0] * r.getX() + m[2][1] * r.getY() + m[2][2] * r.getZ() + m[2][3])
				;
	}
	
	public Matrix4f mul(Matrix4f r)
	{
		Matrix4f res = new Matrix4f();
		
		for (int i = 0; i < 4; i ++)
			for (int j = 0; j < 4; j ++)
				res.set(i, j, 	m[i][0] * r.m[0][j] +
								m[i][1] * r.m[1][j] +
								m[i][2] * r.m[2][j] +
								m[i][3] * r.m[3][j]);
		return res;
	}
	
	public float[][] getM() {
		return m.clone();
	}

	public float get(int x, int y)
	{
		return m[x][y];
	}
	
	public void setM(float[][] m) {
		this.m = m;
	}

	public void set(int x, int y, float val)
	{
		m[x][y] = val;
	}
	
}
