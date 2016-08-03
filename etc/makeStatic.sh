#!/bin/bash
BASE_PATH=/usr/local/independence/apache2/htdocs

curl -o $BASE_PATH/index.html http://localhost:80/index

curl -o $BASE_PATH/static/post/scala/intro.md.html http://localhost:80/post/detail/1
curl -o $BASE_PATH/static/post/scala/development_environment.md.html http://localhost:80/post/detail/2
curl -o $BASE_PATH/static/post/scala/data_types.md.html http://localhost:80/post/detail/3
curl -o $BASE_PATH/static/post/scala/operators.md.html http://localhost:80/post/detail/4
curl -o $BASE_PATH/static/post/scala/control_structures.md.html http://localhost:80/post/detail/5
curl -o $BASE_PATH/static/post/scala/classes.md.html http://localhost:80/post/detail/6
curl -o $BASE_PATH/static/post/scala/inheritance.md.html http://localhost:80/post/detail/7
curl -o $BASE_PATH/static/post/scala/case_classes.md.html http://localhost:80/post/detail/8
curl -o $BASE_PATH/static/post/scala/functional_programming.md.html http://localhost:80/post/detail/9
curl -o $BASE_PATH/static/post/scala/higher_order_function_p1.md.html http://localhost:80/post/detail/10
curl -o $BASE_PATH/static/post/scala/higher_order_function_p2.md.html http://localhost:80/post/detail/11

curl -o $BASE_PATH/static/post/scala/index.html http://localhost:80/categories/scala

curl -o $BASE_PATH/static/about_me.html http://localhost:80/about_me

curl -o $BASE_PATH/static/resume.html http://localhost:80/about_me/resume

