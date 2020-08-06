'use strict';

class rangelist extends React.Component {

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
	
	
	
	generateResult() {
		return React.createElement("div", {key: 'userslist', id: 'userlist'},
			this.state.ranges.map(
				function(listValue){
					return React.createElement("div", {key: "range_" + listValue, className: 'range'}, [
						React.createElement("p", {key: "p_" + listValue, className: 'asdf'}, listValue)
						]
					);
				}
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
		React.createElement(rangelist, {id: 'rangelist', key: 'rangelist'}),
		document.getElementById('content')
);