#version 330 core

layout(location = 1) in vec3 position;

out vec3 color;

void main(){
	color = position;
}