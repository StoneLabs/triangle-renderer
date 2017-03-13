#version 120

varying vec3 position_fs;

void main(){
	gl_FragColor = vec4(position_fs, 1.0);
}

//#version 330 core
//
//layout(location = 1) in vec3 position;
//
//out vec3 color;
//
//void main(){
//	color = position;
//}