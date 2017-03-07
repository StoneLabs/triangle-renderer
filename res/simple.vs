#version 330

layout(location = 0) in vec3 position;
layout(location = 1) out vec3 position_fs;



void main()
{
    gl_Position = vec4(position, 1.0);
    position_fs = (position + vec3(1, 1, 1)) / 2.0f;
}