'use strict';

class Rangelist extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			ranges: []
		}
	}
	
	fetchChanges() {
		fetch('/getranges').then(res => res.json()).then(
			(data) => {
				this.setState({ ranges: data })
			}
		).catch(console.log)
	}
	
	fetchCurrentNumber(uid) {
		var curnum = ""
		fetch("/getcurrentnumber?uid=" + uid).then(res => res.text()).then(
			(data) => {
				curnum += data
			}
		).catch(console.log)
		return React.createElement("label", {key: "label_" + uid}, "Current number: " + curnum);
	}

	componentDidMount() {
		this.fetchChanges()
	}
	
	renderRange(listValue){
		return React.createElement("div", {key: "range_" + listValue, className: 'range'}, [
				React.createElement("form", {key: "form_" + listValue, class: 'form-inline'}, [
					React.createElement("label", {key: "label_" + listValue}, "Range id:"),
					React.createElement("label", {key: "label_" + listValue}, listValue),
					React.createElement("label", {key: "label_" + listValue}, "Request"),
					React.createElement("input", {key: "input_" + listValue}),
					React.createElement("label", {key: "label_" + listValue, style: {marginLeft: "0px"}}, "new number(s): "),
					React.createElement("button", {key: "button_" + listValue}, "Request"),
					this.fetchCurrentNumber(listValue)
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