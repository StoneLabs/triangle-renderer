#version 120

attribute vec3 position;
varying vec3 position_fs;

void main()
{
    gl_Position = vec4(position, 1.0);
    position_fs = (position + vec3(1, 1, 1)) / 2.0f;
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