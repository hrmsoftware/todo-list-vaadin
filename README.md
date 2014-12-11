## todo-list-vaadin
Projektet todo-list-vaadin är ett Maven-projekt som bygger en VAADIN-webbapp.

För att bygga:

`mvn package`

För att köra:

`mvn jetty:run`

Öppna nu webbläsaren mot [http://localhost:8080/](http://localhost:8080/).

För att ändra porten som Jetty använder kan man ange `-Djetty.port=<port>`.

Om det strular så verkar detta vara den stora hammaren som fixar det mesta:

`mvn -Djetty.port=7890 vaadin:compile package jetty:run`

### Referenser

- [VAADIN](http://www.vaadin.com)
- [Hur man bygger, kör, använder debugging, etc.](https://vaadin.com/wiki/-/wiki/Main/Creating+a+Maven+project)
- [GWT](http://www.gwtproject.org/)
