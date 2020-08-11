'use strict';

class Rangelist extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			ranges: []
		}
	}
	
	componentDidMount() {
		fetch('/getranges').then(res => res.json()).then(
			(data) => {
				this.setState({ ranges: data })
			}
		).catch(console.log)
	}
	
	renderRange(listValue){
		return React.createElement("div", {key: "range_" + listValue, className: 'range'}, [
				React.createElement("p", {key: "p_" + listValue}, listValue),
				React.createElement("form", {key: "form_" + listValue}, [
					React.createElement("input", {key: "form_" + listValue}),
					React.createElement("input", {key: "form_" + listValue, type: 'submit'})
				])
			]
		);
	}
	
	generateResult() {
		return React.createElement("div", {key: 'userslist', id: 'userlist'},
			this.state.ranges.map(
				(rangeguid)=>(
						this.renderRange(rangeguid)
				)
			)
		);
	}
	
	render() {
		console.log('Render is called.');
		return React.createElement('div', {id: 'container', key: 'container'},[
			this.generateResult()
		]);
	}
	
}

ReactDOM.render(
		React.createElement(Rangelist, {id: 'rangelist', key: 'rangelist'}),
		document.getElementById('content')
);