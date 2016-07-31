#!/bin/bash
BASE_PATH=/usr/local/independence/apache2/htdocs

curl -o $BASE_PATH/index.html http://localhost:80/index

curl -o $BASE_PATH/post/static/scala/intro.md.html http://localhost:80/post/detail/1
curl -o $BASE_PATH/post/static/scala/development_environment.md.html http://localhost:80/post/detail/2
curl -o $BASE_PATH/post/static/scala/data_types.md.html http://localhost:80/post/detail/3
curl -o $BASE_PATH/post/static/scala/operators.md.html http://localhost:80/post/detail/4
curl -o $BASE_PATH/post/static/scala/control_structures.md.html http://localhost:80/post/detail/5
curl -o $BASE_PATH/post/static/scala/classes.md.html http://localhost:80/post/detail/6
curl -o $BASE_PATH/post/static/scala/inheritance.md.html http://localhost:80/post/detail/7
curl -o $BASE_PATH/post/static/scala/case_classes.md.html http://localhost:80/post/detail/8
curl -o $BASE_PATH/post/static/scala/functional_programming.md.html http://localhost:80/post/detail/9
curl -o $BASE_PATH/post/static/scala/higher_order_function_p1.md.html http://localhost:80/post/detail/10
curl -o $BASE_PATH/post/static/scala/higher_order_function_p2.md.html http://localhost:80/post/detail/11

curl -o $BASE_PATH/post/static/scala/index.html http://localhost:80/categories/scala

