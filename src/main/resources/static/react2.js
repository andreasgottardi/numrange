'use strict';

/*  */
class ContentComponent extends React.Component {

	state = {count: 0}

	constructor(props){
		super(props);
		this.state = props;
	}
	
	generateNewState(){
		return {count: this.state.count + 1}
	}

	componentDidMount() {
        setInterval(() => {
			this.setState(this.generateNewState());
        }, 1000);
	}

	render() {
		console.log("Render called")
		return React.createElement('p', {class: 'text'}, "Content: " + this.state.count);
	}
}

class MyComponent extends React.Component {

	render() {
		return React.createElement('div', {id: 'container', key: 'container'},[
			React.createElement('div', {id: 'left', class: 'col'}, [
				React.createElement('p', {class: 'text'}, 'Menu')
			]),
			React.createElement('div', {id: 'right', class: 'col'}, [
				React.createElement('div', {class: 'text'}, 'Page heading'),
				React.createElement(ContentComponent, {count: 5}),
				React.createElement(ContentComponent, {count: 10}),
				React.createElement(ContentComponent, {count: 15})
			])
		]);
	}
}

ReactDOM.render(
		React.createElement(MyComponent),
		document.getElementById('content')
);